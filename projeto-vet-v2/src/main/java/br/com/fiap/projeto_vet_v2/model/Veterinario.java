package br.com.fiap.projeto_vet_v2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="VETERINARIO")
public class Veterinario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID_VETERINARIO")
	private Long idVeterinario;
	@Column(name="CRV")
	private String crv;
	@Column(name="ESPECIALIDADE")
	private String especialidade;
	@OneToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "ID_CLINICA")
	private Clinica clinica;
	
	public Veterinario() {
		
	}

	public Veterinario(String crv, String especialidade, Usuario usuario, Clinica clinica) {
		this.crv = crv;
		this.especialidade = especialidade;
		this.usuario = usuario;
		this.clinica = clinica;
	}

	public Long getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(Long idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public String getCrv() {
		return crv;
	}

	public void setCrv(String crv) {
		this.crv = crv;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	
	
	
	
}
