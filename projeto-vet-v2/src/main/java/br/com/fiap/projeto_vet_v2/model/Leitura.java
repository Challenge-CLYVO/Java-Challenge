package br.com.fiap.projeto_vet_v2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="LEITURA")
public class Leitura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_LEITURA")
	private Long idLeitura;
	@Column(name="DATA_REGISTRO")
	private LocalDateTime dataRegistro;
	@Column(name="VALOR")
	private Double valor;
	@ManyToOne
	@JoinColumn(name="ID_SENSOR")
	private Sensor sensor;
	
	public Leitura() {
		
	}

	public Leitura(LocalDateTime dataRegistro, Double valor, Sensor sensor) {
		
		this.dataRegistro = dataRegistro;
		this.valor = valor;
		this.sensor = sensor;
	}

	public Long getIdLeitura() {
		return idLeitura;
	}

	public void setIdLeitura(Long idLeitura) {
		this.idLeitura = idLeitura;
	}

	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	
	
	
	
}

