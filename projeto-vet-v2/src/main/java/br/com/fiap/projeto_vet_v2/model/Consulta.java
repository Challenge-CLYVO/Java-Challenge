package br.com.fiap.projeto_vet_v2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CONSULTA")
public class Consulta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CONSULTA")
	private Long idConsulta;
	@Column(name="DATA_HORA")
	private LocalDateTime dataHora;
	@Column(name="MOTIVO")
	private String motivo;
	@Column(name="OBSERVACAO")
	private String observacao;
	@Column(name="STATUS")
	private String status;
	@ManyToOne
	@JoinColumn(name="ID_PET")
	private Pet pet;
	@ManyToOne
	@JoinColumn(name="ID_VETERINARIO")
	private Veterinario veterinario;
	@ManyToOne
	@JoinColumn(name="ID_CLINICA")
	private Clinica clinica;
	
	public Consulta() {
		
	}
	
	public Consulta(LocalDateTime dataHora, String motivo, String observacao, String status, Pet pet,
			Veterinario veterinario, Clinica clinica) {
		
		this.dataHora = dataHora;
		this.motivo = motivo;
		this.observacao = observacao;
		this.status = status;
		this.pet = pet;
		this.veterinario = veterinario;
		this.clinica = clinica;
	}
	public Long getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
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
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public Veterinario getVeterinario() {
		return veterinario;
	}
	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}
	public Clinica getClinica() {
		return clinica;
	}
	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	
	
}
