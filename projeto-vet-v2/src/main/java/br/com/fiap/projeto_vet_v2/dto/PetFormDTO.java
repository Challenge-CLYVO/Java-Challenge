package br.com.fiap.projeto_vet_v2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class PetFormDTO {

	@NotBlank(message="o nome é obrigatório")
	@Size(max=100, message="O nome deve ter no máximo 100 caracteres")
	private String nome;
	@NotBlank(message="O sexo é obrigatório")
	@Size(max=20, message="O sexo deve ter no máximo 20 caracteres")
	private String sexo;
	@NotBlank(message="A raça é obrigatória")
	@Size(max=50, message="A raça deve ter no máximo 50 caracteres")
	private String raca;
	@NotBlank(message="A espécie é obrigatória")
	@Size(max=50, message="a espécie deve ter no máximo 50 caracteres")
	private String especie;
	@NotNull(message = "A data de nascimento é obrigatória")
	@Past(message = "A data de nascimento deve estar no passado")
	private LocalDate dataNascimento;
	
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
	
	
	
	
	
	
}
