package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Sensor;
import br.com.fiap.projeto_vet_v2.repository.SensorRepository;

@Service
public class SensorService {

	@Autowired
	private SensorRepository repoS;
	
	private List<Sensor> listarTodos(){
		return repoS.findAll();
	}
	
	private Optional<Sensor> buscarPorId(Long id){
		return repoS.findById(id);
	}
	
	public List<Sensor> buscarPorPet(Pet pet){
		return repoS.findByPet(pet);
	}
	
	public Sensor salvar(Sensor sensor) {
		return repoS.save(sensor);
	}
	
	public void excluir(Long id) {
		repoS.deleteById(id);
	}
}
