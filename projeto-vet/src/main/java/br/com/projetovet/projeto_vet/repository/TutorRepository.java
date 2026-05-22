package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Tutor;
import br.com.projetovet.projeto_vet.projection.TutorProjection;



public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("""
        SELECT t
        FROM Tutor t
        WHERE UPPER(t.nome) LIKE UPPER(CONCAT('%', :nome, '%'))
    """)
    List<Tutor> retornarTutorPorNome(@Param("nome") String nome);

    @Query("""
        SELECT
            t.nome AS nome,
            t.email AS email
        FROM Tutor t
        WHERE UPPER(t.nome) LIKE UPPER(CONCAT('%', :substring, '%'))
    """)
    List<TutorProjection> retornarTutorPorSubstring(@Param("substring") String substring);
}
