package br.com.fiap.projeto_vet_v2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService{

	
	@Autowired
	private UsuarioRepository repoU;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		Usuario usuario = repoU.findByEmail(email).orElseThrow(()-> 
		new UsernameNotFoundException("Usuário não encontrado"));
				
		return User.withUsername(usuario.getEmail()).password(usuario.getSenha())
				.authorities(usuario.getTipoUsuario()).disabled(!usuario.getStatus().equals("ATIVO")).build();
				
				
		
	}
		
	
}
