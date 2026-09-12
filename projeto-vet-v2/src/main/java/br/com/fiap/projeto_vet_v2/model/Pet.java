package br.com.fiap.projeto_vet_v2.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "PET")
public class Pet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PET")
	private Long idPet;
	@Column(name="NOME")
	private String nome;
	@Column(name="SEXO")
	private String sexo;
	@Column(name="RACA")
	private String raca;
	@Column(name="ESPECIE")
	private String especie;
	@Column(name="DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	@ManyToOne
	@JoinColumn(name="ID_RESPONSAVEL")
	private Responsavel responsavel;
	
	public Pet() {
		
	}
	
	public Pet(String nome, String sexo, String raca, String especie, LocalDate dataNascimento,
			Responsavel responsavel) {
		this.nome = nome;
		this.sexo = sexo;
		this.raca = raca;
		this.especie = especie;
		this.dataNascimento = dataNascimento;
		this.responsavel = responsavel;
	}
	
	public Long getIdPet() {
		return idPet;
	}
	public void setIdPet(Long idPet) {
		this.idPet = idPet;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	
	

	
	
}
