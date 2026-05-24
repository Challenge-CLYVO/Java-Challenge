package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Historico_saude;
import br.com.projetovet.projeto_vet.projection.Historico_saudeProjection;

public interface Historico_saudeRepository extends JpaRepository<Historico_saude, Long> {

	
	 @Query("""
		        SELECT h
		        FROM Historico_saude h
		        WHERE UPPER(h.pet.nome) LIKE UPPER(CONCAT('%', :pet, '%'))
		    """)
		    List<Historico_saude> retornarHistorico_saudePorPet(@Param("pet") String pet);

		    @Query("""
		        SELECT
		            h.descricao AS descricao,
		            h.dataRegistro AS dataRegistro,
		            p.nome AS nomePet
		        FROM Historico_saude h
		        JOIN h.pet p
		        WHERE UPPER(h.descricao) LIKE UPPER(CONCAT('%', :substring, '%'))
		    """)
		    List<Historico_saudeProjection> retornarHistorico_saudePorSubString(@Param("substring") String substring);
	
	
}
