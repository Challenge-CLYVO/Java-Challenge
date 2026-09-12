package br.com.fiap.projeto_vet_v2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CLINICA")
public class Clinica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CLINICA")
	private Long idClinica;
	@Column(name="NOME")
	private String nome;
	@Column(name="CNPJ")
	private String cnpj;
	@Column(name="TELEFONE")
	private String telefone;
	@Column(name="EMAIL")
	private String email;
	@Column(name="ENDERECO")
	private String endereco;
	
	public Clinica() {
		
	}

	public Clinica(String nome, String cnpj, String telefone, String email, String endereco) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public Long getIdClinica() {
		return idClinica;
	}

	public void setIdClinica(Long idClinica) {
		this.idClinica = idClinica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	
	
	
	
	
}
