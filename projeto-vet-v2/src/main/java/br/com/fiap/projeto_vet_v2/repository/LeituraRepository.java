package br.com.fiap.projeto_vet_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Leitura;
import br.com.fiap.projeto_vet_v2.model.Sensor;

public interface LeituraRepository extends JpaRepository <Leitura , Long> {

	List<Leitura> findBySensor(Sensor sensor);
	
}
