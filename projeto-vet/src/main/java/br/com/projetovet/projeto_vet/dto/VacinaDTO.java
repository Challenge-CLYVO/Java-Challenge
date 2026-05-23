package br.com.projetovet.projeto_vet.dto;

import br.com.projetovet.projeto_vet.model.Vacina;

public class VacinaDTO {

	private Long id;
	private String nome;
	private String descricao;
	
	public VacinaDTO() {
		
	}

	public VacinaDTO(Long id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public VacinaDTO(Vacina vacina) {
		this.id = vacina.getId();
		this.nome = vacina.getNome();
		this.descricao = vacina.getDescricao();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
