package br.com.fiap.projeto_vet_v2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
	
}
