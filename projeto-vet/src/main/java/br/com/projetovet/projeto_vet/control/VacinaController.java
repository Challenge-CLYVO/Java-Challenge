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

import br.com.projetovet.projeto_vet.model.Vacina;
import br.com.projetovet.projeto_vet.repository.VacinaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/vacina")
public class VacinaController {

	@Autowired
	private VacinaRepository repoV;
	
	
	@GetMapping(value="todas")
	private List<Vacina> retornarTodasVacinas(){
		
		return repoV.findAll();
	}
	
	
	@GetMapping(value="/{id}")
	private Vacina retornarVacinaPorId(@PathVariable  Long id) {
		
		Optional<Vacina> op = repoV.findById(id);
				
		if(op.isPresent()) {
			
			return op.get();
					
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		
	}

	
	@PostMapping(value="/novo}")
	private Vacina inserirVacina(@RequestBody @Valid Vacina vacina) {
		
		repoV.save(vacina);
		return vacina;
		
	}
	
	
	
	@DeleteMapping(value="remover/{id}")
	private Vacina deletarVacina(@PathVariable @Valid Long id) {
		
		Optional<Vacina> op = repoV.findById(id);
		
		if(op.isPresent()){
			
			repoV.delete(op.get());
			
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
			
		
	}
	
	
	@PutMapping(value="atualizar/{id}")
	private Vacina atualizarVacina(@PathVariable @Valid Long id, @RequestBody Vacina vacina) {
		
		Optional<Vacina> op = repoV.findById(id);
		
		if(op.isPresent()) {
			
			Vacina vacinaBanco = op.get();
			vacinaBanco.transferirVacina(vacina);
			repoV.save(vacinaBanco);
			return vacinaBanco;
		
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	

}
	
	
	
