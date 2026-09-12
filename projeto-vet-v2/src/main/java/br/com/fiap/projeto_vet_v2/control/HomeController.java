package br.com.fiap.projeto_vet_v2.control;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.service.UsuarioService;

@Controller
public class HomeController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public String home(Principal principal) {
		
		Usuario usuario = usuarioService.buscarPorEmail(principal.getName()).orElseThrow(()->
		new RuntimeException("Usuário não encontrado")
				);
		if (usuario.getTipoUsuario().equals("RESPONSAVEL")) {
			return "redirect:/responsavel/pets";
		}
		if (usuario.getTipoUsuario().equals("VETERINARIO")) {
			return "redirect:/veterinario/consultas";
		}
		if (usuario.getTipoUsuario().equals("ADMIN")) {
	        return "redirect:/admin/veterinarios/novo";
	    }
		
		return "redirect:/login";
	}
}
