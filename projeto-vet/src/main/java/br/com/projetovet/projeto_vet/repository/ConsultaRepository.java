package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Consulta;
import br.com.projetovet.projeto_vet.projection.ConsultaProjection;

public interface ConsultaRepository extends JpaRepository <Consulta, Long> {

	@Query("""
	        SELECT c
	        FROM Consulta c
	        WHERE UPPER(c.descricao) LIKE UPPER(CONCAT('%', :descricao, '%'))
	    """)
	    List<Consulta> retornarConsultaPorDescricao(@Param("descricao") String descricao);

	    @Query("""
	        SELECT
	            c.dataConsulta AS dataConsulta,
	            c.descricao AS descricao,
	            c.pet.nome AS nomePet,
	            c.clinica.nome AS nomeClinica
	        FROM Consulta c
	        WHERE UPPER(c.descricao) LIKE UPPER(CONCAT('%', :substring, '%'))
	    """)
	    List<ConsultaProjection> retornarConsultaPorSubstring(@Param("substring") String substring);
	
	
	
	
}
