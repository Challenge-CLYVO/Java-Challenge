package br.com.projetovet.projeto_vet.projection;

import java.time.LocalDate;

public interface Aplicacao_vacinaProjection {

    LocalDate getDataAplicacao();
    String getNomeVacina();
    String getNomePet();
    
}
