package br.com.fiap.projeto_vet_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Lembrete;
import br.com.fiap.projeto_vet_v2.model.Pet;

public interface LembreteRepository extends JpaRepository<Lembrete, Long>{

	List<Lembrete> findByPet(Pet pet);
	
}
