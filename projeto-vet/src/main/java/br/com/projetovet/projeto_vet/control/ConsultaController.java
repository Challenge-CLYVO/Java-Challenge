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

import br.com.projetovet.projeto_vet.model.Consulta;
import br.com.projetovet.projeto_vet.repository.ConsultaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaRepository repoC;
	
	@GetMapping(value="/todas")
	private List<Consulta> retornarTodasConsultas(){
		
		return repoC.findAll();
	}
	
	@GetMapping(value="/{id}")
	private Consulta retornarConsultaPorId(@PathVariable Long id) {
		
	Optional<Consulta> op = repoC.findById(id);
	
	if(op.isPresent()) {
		
		return op.get();
		
	}else {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	}
	
	@PostMapping(value="/novo")
	private Consulta inserirConsulta(@RequestBody Consulta consulta) {
		
		repoC.save(consulta);
		return consulta;
		
	}
	
	
	@DeleteMapping(value="/remover{id}")
	private Consulta deletarConsulta(@PathVariable @Valid Long id) {
		
		Optional<Consulta> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			 repoC.delete(op.get());
			 return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PutMapping(value="/atualizar{id}")
	private Consulta atualizarConsulta(@PathVariable @Valid Long id,@RequestBody Consulta consulta) {
		
		Optional<Consulta> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			Consulta bancoConsulta = op.get();
			bancoConsulta.transferirConsulta(bancoConsulta);
			repoC.save(bancoConsulta);
			return bancoConsulta;
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		
		
	}
		

}
