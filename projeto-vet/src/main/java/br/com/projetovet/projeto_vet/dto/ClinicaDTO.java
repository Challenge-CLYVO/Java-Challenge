package br.com.projetovet.projeto_vet.dto;

import br.com.projetovet.projeto_vet.model.Clinica;

public class ClinicaDTO {

	private Long id;
	private String nome;
	private String endereco;
	private String telefone;
	
	public ClinicaDTO() {
	
	}

	public ClinicaDTO(Long id, String nome, String endereco, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public ClinicaDTO(Clinica clinica) {
		this.id = clinica.getId();
		this.nome = clinica.getNome();
		this.telefone = clinica.getTelefone();
		this.endereco = clinica.getEndereco();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
}
