package br.com.projetovet.projeto_vet.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="historico_saude")
public class Historico_saude {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Descrição é um campo obrigatório")
	private String descricao;
	private LocalDate dataRegistro;
	@ManyToOne
	@JoinColumn(name="id_pet")
	private Pet pet;
	
	public Historico_saude() {
		
	}

	public Historico_saude(Long id, String descricao, LocalDate dataRegistro, Pet pet) {
	
		this.id = id;
		this.descricao = descricao;
		this.dataRegistro = dataRegistro;
		this.pet = pet;
	}
	
	public void transferirHistorico_saude(Historico_saude historico_saude) {
		this.descricao = historico_saude.getDescricao();
		this.dataRegistro = historico_saude.getDataRegistro();
		this.pet= historico_saude.getPet();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	
	
}
