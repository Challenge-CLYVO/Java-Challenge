package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_vet_v2.model.Leitura;
import br.com.fiap.projeto_vet_v2.model.Sensor;
import br.com.fiap.projeto_vet_v2.repository.LeituraRepository;

@Service
public class LeituraService {

	@Autowired
	private LeituraRepository repoL;
	
	public List<Leitura> listarTodos(){
		return repoL.findAll();
	}
	
	public Optional<Leitura> buscarPorId(Long id){
		return repoL.findById(id);
	}
	
	public List<Leitura> buscarPorSensor(Sensor sensor){
		return repoL.findBySensor(sensor);
	}
	
	public Leitura salvar(Leitura leitura) {
		return repoL.save(leitura);
	}
	
	public void excluir(Long id) {
		repoL.deleteById(id);
	}
}
