package br.com.projetovet.projeto_vet.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "Entidade responsável pelas consultas")
@Entity
@Table(name="consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataConsulta;
	@NotEmpty(message="Descrição é um campo obrigatório")
	private String descricao;
	@ManyToOne
	@JoinColumn(name="id_pet")
	private Pet pet;
	@ManyToOne
	@JoinColumn(name="id_clinica")
	private Clinica clinica;
	
	public Consulta() {
		
	}

	public Consulta(Long id, LocalDate dataConsulta, String descricao, Pet pet, Clinica clinica) {
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.descricao = descricao;
		this.pet = pet;
		this.clinica = clinica;
	}
	
	public void transferirConsulta(Consulta consulta) {
		this.dataConsulta = consulta.getDataConsulta();
		this.descricao = consulta.getDescricao();
		this.pet = consulta.getPet();
		this.clinica = consulta.getClinica();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	
	
	
	
	
}
