package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Vacina;
import br.com.projetovet.projeto_vet.projection.VacinaProjection;

public interface VacinaRepository extends JpaRepository <Vacina, Long> {

	@Query("""
	        SELECT v
	        FROM Vacina v
	        WHERE UPPER(v.nome) LIKE UPPER(CONCAT('%', :nome, '%'))
	    """)
	    List<Vacina> retornarVacinaPorNome(@Param("nome") String nome);

	    @Query("""
	        SELECT
	            v.id AS id,
	            v.nome AS nome,
	            v.descricao AS descricao
	        FROM Vacina v
	        WHERE UPPER(v.nome) LIKE UPPER(CONCAT('%', :substring, '%'))
	    """)
	    List<VacinaProjection> retornarVacinaPorSubstring(@Param("substring") String substring);
	
}

