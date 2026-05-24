package br.com.projetovet.projeto_vet.dto;

import java.time.LocalDate;

import br.com.projetovet.projeto_vet.model.Aplicacao_vacina;

public class Aplicacao_vacinaDTO {

	private Long id;
	private LocalDate dataAplicacao;
	private String nomeVacina;
	private String nomePet;
	
	public Aplicacao_vacinaDTO() {
		
	}

	public Aplicacao_vacinaDTO(Long id, LocalDate dataAplicacao, String nomeVacina, String nomePet) {
		super();
		this.id = id;
		this.dataAplicacao = dataAplicacao;
		this.nomeVacina = nomeVacina;
		this.nomePet = nomePet;
	}

	public Aplicacao_vacinaDTO(Aplicacao_vacina aplicacao_vacina) {
		this.id = aplicacao_vacina.getId();
		this.dataAplicacao = aplicacao_vacina.getDataAplicacao();
		this.nomePet = aplicacao_vacina.getPet().getNome();
		this.nomeVacina = aplicacao_vacina.getVacina().getNome();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(LocalDate dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public String getNomePet() {
		return nomePet;
	}

	public void setNomePet(String nomePet) {
		this.nomePet = nomePet;
	}
	
	
	
	
}
