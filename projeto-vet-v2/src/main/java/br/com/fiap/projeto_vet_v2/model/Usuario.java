package br.com.fiap.projeto_vet_v2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIO")

public class Usuario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private Long  idUsuario;
	@Column(name="NOME")
	private String nome;
	@Column(name="EMAIL")
	private String email;
	@Column(name="SENHA")
	private String senha;
	@Column(name="TELEFONE")
	private String telefone;
	@Column(name="DATA_CADASTRO")
	private LocalDateTime dataCadastro;
	@Column(name="STATUS")
	private String status;
	@Column(name="TIPO_USUARIO")
	private String tipoUsuario;
	
	public Usuario() {
		
	}

	public Usuario(String nome, String email, String senha, String telefone,
            LocalDateTime dataCadastro, String status, String tipoUsuario) {

		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.tipoUsuario = tipoUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
	
	
	
	
	
	
	
}

