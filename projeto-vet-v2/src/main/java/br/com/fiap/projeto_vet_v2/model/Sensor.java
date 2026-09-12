package br.com.fiap.projeto_vet_v2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="SENSOR")
public class Sensor {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID_SENSOR")
	private Long idSensor;
	@Column(name="TIPO")
	private String tipo;	
	@Column(name="UNIDADE")
	private String unidade;
	@Column(name="STATUS")
	private String status;	
	@ManyToOne
	@JoinColumn(name="ID_PET")
	private Pet pet;
	
	public Sensor() {
	
	}

	public Sensor(String tipo, String unidade, String status, Pet pet) {
		
		this.tipo = tipo;
		this.unidade = unidade;
		this.status = status;
		this.pet = pet;
	}

	public Long getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(Long idSensor) {
		this.idSensor = idSensor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	
	
	
	
}
