package br.com.fiap.projeto_vet_v2.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConsultaAgendamentoDTO {

	@NotNull(message="A data e hora são obrigatórias")
	@Future(message="A consulta deve ser agendada para uma data futura")
	private LocalDateTime dataHora;
	@NotBlank(message="O motivo é obrigatório")
	@Size(max=100, message="O motivo deve ter no máximo 100 caracteres")
	private String motivo;
	@NotNull(message="O pet é obrigatório")
	private Long idPet;
	@NotNull(message="O veterinário é obrigatório")
	private Long idVeterinario;
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Long getIdPet() {
		return idPet;
	}
	public void setIdPet(Long idPet) {
		this.idPet = idPet;
	}
	public Long getIdVeterinario() {
		return idVeterinario;
	}
	public void setIdVeterinario(Long idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	
	
	
	
}
