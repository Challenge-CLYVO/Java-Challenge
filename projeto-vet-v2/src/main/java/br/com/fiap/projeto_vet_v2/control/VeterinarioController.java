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

import br.com.fiap.projeto_vet_v2.dto.ConsultaFinalizacaoDTO;
import br.com.fiap.projeto_vet_v2.model.Consulta;
import br.com.fiap.projeto_vet_v2.model.Leitura;
import br.com.fiap.projeto_vet_v2.model.Sensor;
import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.model.Veterinario;
import br.com.fiap.projeto_vet_v2.service.ConsultaService;
import br.com.fiap.projeto_vet_v2.service.LeituraService;
import br.com.fiap.projeto_vet_v2.service.SensorService;
import br.com.fiap.projeto_vet_v2.service.UsuarioService;
import br.com.fiap.projeto_vet_v2.service.VeterinarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

	
	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private LeituraService leituraService;
    
    @GetMapping("/consultas")
    public String listarConsultas(Principal principal, Model model) {
    	
    	
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(() ->
    	new RuntimeException("Usuario não encontrado"));
    	
    	Veterinario veterinario = veterinarioService.buscarPorUsuario(usuario).orElseThrow(() ->
    	new RuntimeException("Veterinário não encontrado"));
    			
    	model.addAttribute(
    			"consultas",
    			consultaService.buscarPorVeterinario(veterinario));
    			
    	return "consulta/lista-veterinario";
    	
    }
    
    @GetMapping("/consultas/{idConsulta}")
    public String detalhesConsulta(@PathVariable Long idConsulta,Principal principal,Model model) {
    
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(() ->
    	new RuntimeException("Usuário não encontrado"));
    	
    	Veterinario veterinario = veterinarioService.buscarPorUsuario(usuario).orElseThrow(() ->
    	new RuntimeException("Veterinário não encontrado"));
    	
    	Consulta consulta = consultaService.buscarPorId(idConsulta).orElseThrow(() ->
    	new RuntimeException("Consulta não encontrada"));
    	
    	if (!consulta.getVeterinario().getIdVeterinario().equals(veterinario.getIdVeterinario())) {
    
    		throw new RuntimeException("Esta consulta não pertence ao veterinário logado");
    		
    }

    List<Sensor> sensores = sensorService.buscarPorPet(consulta.getPet());
    
    Map<Long,List<Leitura>> leiturasPorSensor = new LinkedHashMap<>();
    
    for (Sensor sensor :sensores) {
    	
    	leiturasPorSensor.put(sensor.getIdSensor(),leituraService.buscarPorSensor(sensor));
    }
    
    model.addAttribute("consulta", consulta);
    model.addAttribute("sensores", sensores);
    model.addAttribute("leiturasPorSensor", leiturasPorSensor);
    model.addAttribute("finalizacao", new ConsultaFinalizacaoDTO());
    
    return "consulta/detalhes-veterinario";
    
    
    }
    
    @PostMapping("/consultas/{idConsulta}/finalizar")
    public String finalizarConsulta(@PathVariable Long idConsulta, @Valid@ModelAttribute("finalizacao")ConsultaFinalizacaoDTO dto, BindingResult result, Principal principal, Model model) {
    
    	Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(() ->	
    	new RuntimeException("Usuário não encontrado"));
    	
    	Veterinario veterinario = veterinarioService.buscarPorUsuario(usuario).orElseThrow(() ->
    	new RuntimeException("Veterinário não encontrado"));
    	
    	if (result.hasErrors()) {
    		
    		return "redirect:/veterinario/consultas/" + idConsulta;
    	}
    	
    	consultaService.finalizarConsulta(idConsulta,dto,veterinario);
    	
    	return "redirect:/veterinario/consultas";
    	
    }
}
