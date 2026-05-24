package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Aplicacao_vacina;
import br.com.projetovet.projeto_vet.projection.Aplicacao_vacinaProjection;

public interface Aplicacao_vacinaRepository extends JpaRepository<Aplicacao_vacina, Long> {

	@Query("""
	        SELECT a
	        FROM Aplicacao_vacina a
	        WHERE UPPER(a.pet.nome) LIKE UPPER(CONCAT('%', :pet, '%'))
	    """)
	    List<Aplicacao_vacina> retornarAplicacao_vacinaPorPet(@Param("pet") String pet);

	    @Query("""
	        SELECT a
	        FROM Aplicacao_vacina a
	        WHERE UPPER(a.vacina.nome) LIKE UPPER(CONCAT('%', :vacina, '%'))
	    """)
	    List<Aplicacao_vacina> retornarAplicacao_vacinaPorVacina(@Param("vacina") String vacina);

	    @Query("""
	        SELECT
	            a.dataAplicacao AS dataAplicacao,
	            v.nome AS nomeVacina,
	            p.nome AS nomePet
	        FROM Aplicacao_vacina a
	        JOIN a.vacina v
	        JOIN a.pet p
	        WHERE UPPER(p.nome) LIKE UPPER(CONCAT('%', :substring, '%'))
	    """)
	    List<Aplicacao_vacinaProjection> retornarAplicacao_vacinaPorSubstring(@Param("substring") String substring);
}
