package br.com.fiap.projeto_vet_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Consulta;
import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Veterinario;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	List<Consulta> findByPet(Pet pet);
	
	List<Consulta> findByVeterinario(Veterinario veterinario);
	
}
