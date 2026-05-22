package br.com.projetovet.projeto_vet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vacina")
public class Vacina {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
}
