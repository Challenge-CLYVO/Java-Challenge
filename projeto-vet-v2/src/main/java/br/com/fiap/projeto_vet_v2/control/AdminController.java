package br.com.fiap.projeto_vet_v2.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import br.com.fiap.projeto_vet_v2.dto.VeterinarioCadastroDTO;
import br.com.fiap.projeto_vet_v2.model.Clinica;
import br.com.fiap.projeto_vet_v2.repository.ClinicaRepository;
import br.com.fiap.projeto_vet_v2.service.VeterinarioService;
import jakarta.validation.Valid;

@Controller
public class AdminController {

	@Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private ClinicaRepository clinicaRepository;
    
    @GetMapping("/admin/veterinarios/novo")
    public String formularioVeterinario(Model model) {
    	
    	model.addAttribute(
                "cadastro",
                new VeterinarioCadastroDTO()
            );
    	model.addAttribute(
    	          "clinicas",
    	          clinicaRepository.findAll()
    	        );

    	return "admin/cadastro-veterinario";
    	
    	
    }
    
    @PostMapping("/admin/veterinarios/novo")
    public String cadastrarVeterinario(@Valid @ModelAttribute("cadastro")VeterinarioCadastroDTO dto,BindingResult result,Model model) {
    	
    	
    	if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
    		
    		result.reject(
                    "senha",
                    "As senhas não coincidem"
                );
    	}
    	 if (result.hasErrors()) {
             model.addAttribute(
                 "clinicas",
                 clinicaRepository.findAll()
             );

             return "admin/cadastro-veterinario";
         }
    	
    	 Clinica clinica = clinicaRepository.findById(dto.getIdClinica()).orElseThrow( () -> new RuntimeException(
    			 "Clínica não encontrada"
    			 )
    			 );
    	 veterinarioService.cadastrar(
    	            dto.getNome(),
    	            dto.getEmail(),
    	            dto.getSenha(),
    	            dto.getTelefone(),
    	            dto.getCrv(),
    	            dto.getEspecialidade(),
    	            clinica
    	        );
    	        return "redirect:/admin/veterinarios/novo?sucesso";
       
    }
	
	
}
