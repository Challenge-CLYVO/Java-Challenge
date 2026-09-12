package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_vet_v2.model.Lembrete;
import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.repository.LembreteRepository;

@Service
public class LembreteService {

	@Autowired
	private LembreteRepository repoL;
	
	public List<Lembrete> listarTodos(){
		return repoL.findAll();
	}
	
	public Optional<Lembrete> buscarPorId(Long id){
		return repoL.findById(id);
	}
	
	public List<Lembrete> buscarPorPet(Pet pet){
		return repoL.findByPet(pet);
	}
	
	public Lembrete salvar(Lembrete lembrete) {
		return repoL.save(lembrete);
	}
	
	public void excluir(Long id) {
		repoL.deleteById(id);
	}
	
}
