package br.com.projetovet.projeto_vet.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Entidade responsável pelos tutores")
@Entity
@Table(name  ="tutor")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Nome é um campo obrigatório")
	private String nome;
	@NotEmpty(message="Telefone obrigatorio")
	@Size(min=10, max=11, message="O tamanho da String" + "deve estar entre 10 e 11")
	private String telefone;
	@Email(message="Email inválido")
	private String email;
	
	
	public Tutor() {

	}
	
	public void transferirTutor(Tutor tutor) {

		this.nome = tutor.getNome();
		this.telefone = tutor.getTelefone();
		this.email = tutor.getEmail();
	}
	
	public Tutor(Long id, String nome, String telefone, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}