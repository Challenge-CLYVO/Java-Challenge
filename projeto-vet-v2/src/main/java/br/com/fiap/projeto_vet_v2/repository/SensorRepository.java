package br.com.fiap.projeto_vet_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

	List<Sensor> findByPet(Pet pet);
	
}
