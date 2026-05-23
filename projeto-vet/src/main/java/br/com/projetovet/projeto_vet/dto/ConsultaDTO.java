package br.com.projetovet.projeto_vet.dto;

import java.time.LocalDate;

import br.com.projetovet.projeto_vet.model.Consulta;

public class ConsultaDTO {

	private Long id;
	private LocalDate dataConsulta;
	private String descricao;
	private String nomePet;
	private String nomeClinica;
	
	public ConsultaDTO() {
		
	}

	public ConsultaDTO(Long id, LocalDate dataConsulta, String descricao, String nomePet, String nomeClinica) {
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.descricao = descricao;
		this.nomePet = nomePet;
		this.nomeClinica = nomeClinica;
	}
	
	public ConsultaDTO(Consulta consulta) {
		this.id = consulta.getId();
		this.descricao = consulta.getDescricao();
		this.dataConsulta = consulta.getDataConsulta();
		this.nomePet = consulta.getPet().getNome();
		this.nomeClinica = consulta.getClinica().getNome();

		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomePet() {
		return nomePet;
	}

	public void setNomePet(String nomePet) {
		this.nomePet = nomePet;
	}

	public String getNomeClinica() {
		return nomeClinica;
	}

	public void setNomeClinica(String nomeClinica) {
		this.nomeClinica = nomeClinica;
	}
	
	

	
}
	
	