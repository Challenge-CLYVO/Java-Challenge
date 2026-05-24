package br.com.projetovet.projeto_vet.dto;

import br.com.projetovet.projeto_vet.model.Historico_saude;

public class Historico_saudeDTO {

	private Long id;
	private String descricao;
	private String nomePet;
	
	public Historico_saudeDTO() {
		
	}

	public Historico_saudeDTO(Long id, String descricao, String nomePet) {
	
		this.id = id;
		this.descricao = descricao;
		this.nomePet = nomePet;
	}
	
	
	public Historico_saudeDTO(Historico_saude historico_saude) {
		this.id = historico_saude.getId();
		this.descricao = historico_saude.getDescricao();
		this.nomePet = historico_saude.getPet().getNome();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
