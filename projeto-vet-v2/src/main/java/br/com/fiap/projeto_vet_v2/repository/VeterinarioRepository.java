package br.com.fiap.projeto_vet_v2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Clinica;
import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.model.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario,Long> {
	
	Optional<Veterinario> findByUsuario(Usuario usuario);

    List<Veterinario> findByClinica(Clinica clinica);
	
}
