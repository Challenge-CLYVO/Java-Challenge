package br.com.fiap.projeto_vet_v2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ConsultaFinalizacaoDTO {

    @NotBlank
    @Size(max = 255)
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}