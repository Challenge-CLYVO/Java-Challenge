package br.com.projetovet.projeto_vet.dto;

import br.com.projetovet.projeto_vet.model.Pet;
import br.com.projetovet.projeto_vet.model.Tutor;

public class PetDTO {

	private Long id;
	private String nome;
	private Integer idade;
	private String especie;
	private String raca;
	private Tutor tutor;
	
	public PetDTO() {
		
	}

	public PetDTO(Long id, String nome, Integer idade, String especie, String raca, Tutor tutor) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.especie = especie;
		this.raca = raca;
		this.tutor = tutor;
	}
	
	public PetDTO(Pet pet) {
		this.id = pet.getId();
		this.especie =pet.getEspecie();
		this.nome =pet.getNome();
		this.raca = pet.getRaca();
		this.idade =pet.getIdade();
		this.tutor= pet.getTutor();
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	
	
	
	
}
