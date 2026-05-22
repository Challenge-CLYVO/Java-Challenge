package br.com.projetovet.projeto_vet.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.projetovet.projeto_vet.model.Clinica;
import br.com.projetovet.projeto_vet.repository.ClinicaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaRepository repoC;
	
	@GetMapping(value="/todas")
	private List<Clinica> retornarTodasClinicas(){
		return repoC.findAll();
	}
	
	@GetMapping(value="/{id}")
	private Clinica retornarClinicaPorId(@PathVariable @Valid Long id){
		
		Optional<Clinica> op = repoC.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value="/novo")
	private Clinica inserirClinica(@RequestBody @Valid Clinica clinica) {
		
		repoC.save(clinica);
		return clinica;
		
	}
	
	@DeleteMapping(value="remover/{id}")
	private Clinica removerClinica(@PathVariable @Valid Long id) {
		
		Optional<Clinica> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			repoC.delete(op.get());
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
			
		
		
	}
	
	@PutMapping(value="atualizar/{id}")
	private Clinica atualizarClinica(@PathVariable @Valid Long id ,@RequestBody Clinica clinica) {
		
		Optional<Clinica> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			Clinica clinicaBanco = op.get();
			clinicaBanco.transferirClinica(clinica);
			return clinicaBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
