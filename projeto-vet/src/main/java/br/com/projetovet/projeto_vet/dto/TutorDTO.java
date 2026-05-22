package br.com.projetovet.projeto_vet.dto;

import br.com.projetovet.projeto_vet.model.Tutor;

public class TutorDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public TutorDTO() {

    }

    public TutorDTO(Tutor tutor){

        this.id = tutor.getId();
        this.nome = tutor.getNome();
        this.telefone = tutor.getTelefone();
        this.email = tutor.getEmail();

    }

    public TutorDTO(Long id, String nome, String telefone, String email) {
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