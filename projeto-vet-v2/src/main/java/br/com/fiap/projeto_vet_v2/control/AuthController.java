package br.com.fiap.projeto_vet_v2.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.projeto_vet_v2.dto.UsuarioCadastroDTO;
import br.com.fiap.projeto_vet_v2.service.ResponsavelService;
import jakarta.validation.Valid;

@Controller
public class AuthController {

	
	@Autowired
	private ResponsavelService responsavelService;
	
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/cadastro")
	public String formularioCadastro(Model model) {
		
		model.addAttribute(
				"cadastro",
				new UsuarioCadastroDTO()
				);
		
		return"auth/cadastro";
	}
	
	@PostMapping("/cadastro")
	public String cadastrar(@Valid @ModelAttribute("cadastro")UsuarioCadastroDTO dto,BindingResult result) {
		
		if (result.hasErrors()) {
			return "auth/cadastro";
		}
		
		try {
			
			responsavelService.cadastrar(dto);
			
		}catch (RuntimeException e) {
			result.reject(
					"erroCadastro",
					e.getMessage());
					
				return "auth/cadastro";
		}
	
		return "redirect:/login?cadastroSucesso";
	}
	
}
