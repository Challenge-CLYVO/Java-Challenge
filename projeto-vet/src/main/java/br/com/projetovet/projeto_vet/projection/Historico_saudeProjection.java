package br.com.projetovet.projeto_vet.projection;

import java.time.LocalDate;

public interface Historico_saudeProjection {

	String getDescricao();
    LocalDate getDataRegistro();
    String getNomePet();
}
