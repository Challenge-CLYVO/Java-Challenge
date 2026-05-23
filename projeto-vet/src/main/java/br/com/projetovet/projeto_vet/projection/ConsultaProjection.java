package br.com.projetovet.projeto_vet.projection;

import java.time.LocalDate;

public interface ConsultaProjection {
	
	LocalDate getDataConsulta();

    String getDescricao();

    String getNomePet();

    String getNomeClinica();
	
}
