package br.com.projetovet.projeto_vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.projetovet.projeto_vet.model.Clinica;
import br.com.projetovet.projeto_vet.projection.ClinicaProjection;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

    @Query("""
        SELECT c
        FROM Clinica c
        WHERE UPPER(c.nome) LIKE UPPER(CONCAT('%', :nome, '%'))
    """)
    List<Clinica> retornarPorNome(@Param("nome") String nome);

    @Query("""
        SELECT
            c.nome AS nome,
            c.telefone AS telefone,
            c.endereco AS endereco
        FROM Clinica c
        WHERE UPPER(c.nome) LIKE UPPER(CONCAT('%', :substring, '%'))
    """)
    List<ClinicaProjection> retornarPorSubstring(@Param("substring") String substring);
}