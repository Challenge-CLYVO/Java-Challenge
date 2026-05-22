package br.com.projetovet.projeto_vet.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="aplicacao_vacina")
public class Aplicacao_vacina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataAplicacao;
	@ManyToOne
	@JoinColumn(name="id_vacina")
	private Vacina vacina;
	@ManyToOne
	@JoinColumn(name="id_pet")
	private Pet pet;
}
