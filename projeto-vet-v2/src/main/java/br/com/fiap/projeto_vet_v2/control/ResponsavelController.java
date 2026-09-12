package br.com.fiap.projeto_vet_v2.control;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.projeto_vet_v2.dto.ConsultaAgendamentoDTO;
import br.com.fiap.projeto_vet_v2.dto.PetFormDTO;
import br.com.fiap.projeto_vet_v2.model.Leitura;
import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Responsavel;
import br.com.fiap.projeto_vet_v2.model.Sensor;
import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.service.ConsultaService;
import br.com.fiap.projeto_vet_v2.service.LeituraService;
import br.com.fiap.projeto_vet_v2.service.LembreteService;
import br.com.fiap.projeto_vet_v2.service.PetService;
import br.com.fiap.projeto_vet_v2.service.ResponsavelService;
import br.com.fiap.projeto_vet_v2.service.SensorService;
import br.com.fiap.projeto_vet_v2.service.UsuarioService;
import br.com.fiap.projeto_vet_v2.service.VeterinarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/responsavel")
public class ResponsavelController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private PetService petService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private LeituraService leituraService;

    @Autowired
    private LembreteService lembreteService;

  
    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private ConsultaService consultaService;
    
    @GetMapping("/pets")
    public String listarPets(Principal principal, Model model) {
    	
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(()->
    	new RuntimeException("Usuário não encontrado"));
    	
    	Responsavel responsavel = responsavelService.buscarPorUsuario(usuario).orElseThrow(()->
    			new RuntimeException("Responsável não encontrado"));
    	
    	model.addAttribute(
    			"pets",
    			petService.buscarPorResponsavel(responsavel));
    			
    	return "pet/lista";
    }
    
    @GetMapping("/pets/novo")
    public String formularioPet(Model model) {

        model.addAttribute("petForm", new PetFormDTO());

        return "pet/form";
    }
    
    @PostMapping("/pets")
    public String cadastrarPet(@Valid @ModelAttribute("petForm") PetFormDTO dto,BindingResult result,Principal principal) {
    	
    	if (result.hasErrors()) {
            return "pet/form";
        }
    	
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(() ->
    	new RuntimeException("Usuário não encontrado"));
    	 
    	Responsavel responsavel = responsavelService.buscarPorUsuario(usuario) .orElseThrow(() ->
    	 new RuntimeException("Responsável não encontrado"));
    	
    	petService.cadastrar(dto, responsavel);
    
    	return "redirect:/responsavel/pets";
    }
    
    @GetMapping("/pets/{idPet}")
    public String detalhesPet(@PathVariable Long idPet, Principal principal, Model model) {
    	
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(() ->
    			new RuntimeException("Usuário não encontrado"));
    	
    	Responsavel responsavel = responsavelService.buscarPorUsuario(usuario).orElseThrow(() ->
    			new RuntimeException("Responsável não encontrado"));
    
    	Pet pet  = petService.buscarPorId(idPet).orElseThrow(() -> 
    			new RuntimeException("Pet não encontrado"));
    
    	if(!pet.getResponsavel().getIdResponsavel().equals(responsavel.getIdResponsavel())) {
    		
    		throw new RuntimeException("Este pet não pertence ao responsável logado");
    	}
    
    	List<Sensor> sensores = sensorService.buscarPorPet(pet);
    	
    	Map<Long, List<Leitura>> leiturasPorSensor = new LinkedHashMap<>();
    
    	for (Sensor sensor : sensores) {
    		
    		leiturasPorSensor.put(sensor.getIdSensor(),leituraService.buscarPorSensor(sensor));
    	}
    	
    	model.addAttribute("pet", pet);
    	model.addAttribute("sensores", sensores);
    	model.addAttribute("leiturasPorSensor",leiturasPorSensor);
    	model.addAttribute("lembretes",lembreteService.buscarPorPet(pet));
    	
    	return "pet/detalhes";
    	
    }
    
    @GetMapping("/pets/{idPet}/consultas/nova")
    public String formularioConsulta(@PathVariable Long idPet, Model model) {
    
	ConsultaAgendamentoDTO dto = new ConsultaAgendamentoDTO();
    
    dto.setIdPet(idPet);
    
    model.addAttribute("consulta", dto);
    
    model.addAttribute("veterinarios", veterinarioService.listarTodos());
    
    return "consulta/form";
}
    @PostMapping("/consultas")
    public String agendarConsulta(@Valid @ModelAttribute("consulta") ConsultaAgendamentoDTO dto, BindingResult result, Principal principal, Model model){
    	
    	if(result.hasErrors()) {
    		
    		model.addAttribute(
    				"veterinarios",
    				veterinarioService.listarTodos());
    		
    		return "consulta/form";
    		
    	}
    
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(() -> 
    	new RuntimeException("Usuário não encontrado"));
    	
    	Responsavel responsavel = responsavelService.buscarPorUsuario(usuario).orElseThrow(() ->
    	new RuntimeException("Responsável não encontrado"));
    			
    	consultaService.agendarConsulta(dto, responsavel);
    	
    	return "redirect:/responsavel/pets/" + dto.getIdPet();
    }




}

	