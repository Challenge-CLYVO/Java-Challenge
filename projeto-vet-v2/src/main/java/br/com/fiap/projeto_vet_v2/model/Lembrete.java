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
@Table(name="LEMBRETE")
public class Lembrete {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID_LEMBRETE")
	private Long idLembrete;
	@Column(name="TITULO")
	private String titulo;	
	@Column(name="DESCRICAO")
	private String descricao;
	@Column(name="DATA_HORA")
	private LocalDateTime dataHora;
	@Column(name="STATUS")
	private String status;
	@ManyToOne
	@JoinColumn(name="ID_PET")
	private Pet pet;
	
	public Lembrete() {
		
	}

	public Lembrete(String titulo, String descricao, LocalDateTime dataHora, String status, Pet pet) {
		
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.status = status;
		this.pet = pet;
	}

	public Long getIdLembrete() {
		return idLembrete;
	}

	public void setIdLembrete(Long idLembrete) {
		this.idLembrete = idLembrete;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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
	
	
	
	
	
	
	
}
