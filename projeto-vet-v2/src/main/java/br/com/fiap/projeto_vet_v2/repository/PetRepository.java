package br.com.fiap.projeto_vet_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Responsavel;

public interface PetRepository extends JpaRepository<Pet, Long>{

	List<Pet> findByResponsavel(Responsavel responsavel);
}
