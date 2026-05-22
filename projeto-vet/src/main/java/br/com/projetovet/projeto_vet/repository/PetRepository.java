package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Pet;
import br.com.projetovet.projeto_vet.projection.PetProjection;

public interface PetRepository extends JpaRepository <Pet, Long> {

	
	@Query("""
	        SELECT p
	        FROM Pet p
	        WHERE UPPER(p.nome) LIKE UPPER(CONCAT('%', :nome, '%'))
	    """)
	    List<Pet> retornarPetPorNome(@Param("nome") String nome);
	
	@Query("""
		    SELECT p
		    FROM Pet p
		    WHERE UPPER(p.raca) LIKE UPPER(CONCAT('%', :raca, '%'))
		""")
		List<Pet> retornarPetPorRaca(@Param("raca") String raca);
	
	    @Query("""
	        SELECT
	            p.nome AS nome,
	            p.especie AS especie,
	            p.raca AS raca,
	            t.nome AS nomeTutor
	        FROM Pet p
	        JOIN p.tutor t
	        WHERE UPPER(p.nome) LIKE UPPER(CONCAT('%', :substring, '%'))
	    """)
	    List<PetProjection> retornarPetPorSubstring(@Param("substring") String substring);

	    Page<Pet> findAll(Pageable pageable);
	}
		