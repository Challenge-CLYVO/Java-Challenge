package br.com.projetovet.projeto_vet.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.projetovet.projeto_vet.dto.VacinaDTO;
import br.com.projetovet.projeto_vet.model.Vacina;
import br.com.projetovet.projeto_vet.projection.VacinaProjection;
import br.com.projetovet.projeto_vet.repository.VacinaRepository;
import br.com.projetovet.projeto_vet.service.VacinaCachingService;
import br.com.projetovet.projeto_vet.service.VacinaPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/vacina")
public class VacinaController {

	@Autowired
	private VacinaRepository repoV;
	
	@Autowired
	private VacinaPaginacaoService paginacaoV;
	
	@Autowired VacinaCachingService cachingV;
	
	@Operation(
            summary = "Listar vacina paginados",
            description = "Retorna vacina em formato paginado com base em page e size",
            tags = "Retorno de informações da vacina"
        )
	@GetMapping(value="/paginados")
	public ResponseEntity<Page<VacinaDTO>> paginar(
			
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="size", defaultValue="2")Integer size
			){
		
		PageRequest req = PageRequest.of(page, size);
		
		Page<VacinaDTO> paginados = paginacaoV.paginar(req);
		
		return ResponseEntity.ok(paginados);
	}
	

	 @Operation(
	            summary = "Buscar vacina por substring",
	            description = "Busca vacina filtrando por parte do nome ou outros campos via projection",
	            tags = "Retorno de informações da vacina"
	        )
	@GetMapping(value="/substring")
	public List<VacinaProjection>retornarVacinaPorSubstring(@RequestParam String substring){
		
		return cachingV.retornarVacinaPorSubstring(substring);
	}
	
	 @Operation(
	            summary = "Buscar vacina por nome",
	            description = "Retorna lista de vacina filtrados pelo nome",
	            tags = "Retorno de informações da vacina"
	        )
	@GetMapping(value="/por_nome")
	public List<Vacina>retornarVacinaPorNome(@RequestParam String nome){
		
		return cachingV.retornarVacinaPorNome(nome);
	}
	
	 @Operation(
	            summary = "Listar todas as vacinas (cache)",
	            description = "Retorna todas as vacinas utilizando cache para performance",
	            tags = "Retorno de informações da vacina"
	        )
	@GetMapping(value="todas")
	private List<Vacina> retornarTodasVacinas(){
		
		return cachingV.findAll();
	}
	
	 @Operation(
	            summary = "Buscar vacina por ID",
	            description = "Retorna uma vacina específica pelo ID",
	            tags = "Retorno de informações da vacina"
	        )
	@GetMapping(value="/{id}")
	private Vacina retornarVacinaPorId(@PathVariable  Long id) {
		
		Optional<Vacina> op = cachingV.findById(id);
				
		if(op.isPresent()) {
			
			return op.get();
					
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		
	}

	 @Operation(
	            summary = "Criar nova vacina",
	            description = "Insere uma nova vacina no banco e limpa cache",
	            tags = "Inserção de informações da vacina"
	        )
	@PostMapping(value="/{novo}")
	private Vacina inserirVacina(@RequestBody @Valid Vacina vacina) {
		
		repoV.save(vacina);
		cachingV.removerCache();
		return vacina;
		
	}
	
	
	 @Operation(
	            summary = "Remover vacina",
	            description = "Remove uma vacina pelo ID e limpa cache",
	            tags = "Remoção de informações da vacina"
	        )
	@DeleteMapping(value="remover/{id}")
	private Vacina deletarVacina(@PathVariable @Valid Long id) {
		
		Optional<Vacina> op = repoV.findById(id);
		
		if(op.isPresent()){
			
			repoV.delete(op.get());
			cachingV.removerCache();
			
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
			
		
	}
	
	 @Operation(
	            summary = "Atualizar vacina",
	            description = "Atualiza dados de uma vacina existente",
	            tags = "Atualização dos dados da vacina"
	        )
	@PutMapping(value="atualizar/{id}")
	private Vacina atualizarVacina(@PathVariable @Valid Long id, @RequestBody Vacina vacina) {
		
		Optional<Vacina> op = repoV.findById(id);
		
		if(op.isPresent()) {
			
			Vacina vacinaBanco = op.get();
			vacinaBanco.transferirVacina(vacina);
			repoV.save(vacinaBanco);
			cachingV.removerCache();
			return vacinaBanco;
		
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	

}
	
	
	
