package br.com.projetovet.projeto_vet.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Entidade responsável pelos pets")
@Entity
@Table(name="pet")
public class Pet {
 
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Nome é um campo obrigatório.")
	private String nome;
	@NotNull(message="Idade é um campo obrigatório.")
	private Integer idade;
	@NotEmpty(message="Raca é um campo obrigatório.")
	private String raca;
	@NotEmpty(message="Especie é um campo obrigatório.")
	private String especie;
	@ManyToOne
	@JoinColumn(name = "id_tutor")
	private Tutor tutor;
	
	public Pet() {
	
	}

	public Pet(Long id,String nome, String raca,Integer idade,String especie, Tutor tutor) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.raca = raca;
		this.especie = especie;
		this.tutor = tutor;
	}
	
	public void transferirPet(Pet pet) {
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

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	
	
	
	
	
	
}
	
	

	