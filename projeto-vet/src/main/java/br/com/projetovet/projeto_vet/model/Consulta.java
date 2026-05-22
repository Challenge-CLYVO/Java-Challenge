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
@Table(name="consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataConsulta;
	private String descricao;
	@ManyToOne
	@JoinColumn(name="id_pet")
	private Pet pet;
	@ManyToOne
	@JoinColumn(name="id_clinica")
	private Clinica clinca;
}
