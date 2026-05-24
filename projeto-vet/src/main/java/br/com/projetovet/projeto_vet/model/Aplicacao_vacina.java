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

@Schema(description = "Entidade responsável pelas aplicações das vacinas")
@Entity
@Table(name="aplicacao_vacina")
public class Aplicacao_vacina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataAplicacao;
	@ManyToOne
	@JoinColumn(name="id_vacina")
	private Vacina vacina;
	@ManyToOne
	@JoinColumn(name="id_pet")
	private Pet pet;
	
	public Aplicacao_vacina() {
		
	}

	public Aplicacao_vacina(Long id, LocalDate dataAplicacao, Vacina vacina, Pet pet) {

		this.id = id;
		this.dataAplicacao = dataAplicacao;
		this.vacina = vacina;
		this.pet = pet;
	}
	
	public void transfetirAplicacao_vacina(Aplicacao_vacina aplicacao_vacina) {
		this.dataAplicacao = aplicacao_vacina.getDataAplicacao();
		this.pet = aplicacao_vacina.getPet();
		this.vacina = aplicacao_vacina.getVacina();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	
	
	
}
