package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_vet_v2.model.Clinica;
import br.com.fiap.projeto_vet_v2.repository.ClinicaRepository;

@Service
public class ClinicaService {

	@Autowired
	private ClinicaRepository repoC;
	
	public List<Clinica> listarTodos(){
		return repoC.findAll();
	}
	
	public Optional<Clinica> buscarPorId(Long id){
		return repoC.findById(id);
	}
	
	public Clinica salvar(Clinica clinica) {
		return repoC.save(clinica);
	}
	
	public void excluir(Long id) {
		 repoC.deleteById(id);
	}
}
