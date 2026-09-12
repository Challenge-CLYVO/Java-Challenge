package br.com.fiap.projeto_vet_v2.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="RESPONSAVEL")
public class Responsavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_RESPONSAVEL")
	private Long idResponsavel;
	@Column(name="CPF")
	private String cpf;
	@Column(name="DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	@OneToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	
	public Responsavel() {
		
	}
	public Responsavel(String cpf, LocalDate dataNascimento, Usuario usuario) {
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.usuario = usuario;
	}
	public Long getIdResponsavel() {
		return idResponsavel;
	}
	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
