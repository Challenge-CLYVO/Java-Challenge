package br.com.projetovet.projeto_vet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clinica")
public class Clinica {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Nome é um campo obrigatório")
	private String nome;
	@Size(min=10, max=11, message="O tamanho da String" + "deve estar entre 10 e 11")
	private String telefone;
	@NotEmpty(message="Endereço é um campo obrigatório")
	private String endereco;
	
	public Clinica() {
		
	}

	public Clinica(Long id, String nome, String telefone, String endereco) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public void transferirClinica(Clinica clinica) {
		this.nome = clinica.getNome();
		this.endereco = clinica.getEndereco();
		this.telefone = clinica.getTelefone();
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	
}
