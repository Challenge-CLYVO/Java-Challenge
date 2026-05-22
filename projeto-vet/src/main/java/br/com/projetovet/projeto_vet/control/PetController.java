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

import br.com.projetovet.projeto_vet.dto.PetDTO;
import br.com.projetovet.projeto_vet.model.Pet;
import br.com.projetovet.projeto_vet.projection.PetProjection;
import br.com.projetovet.projeto_vet.repository.PetRepository;
import br.com.projetovet.projeto_vet.service.PetCachingService;
import br.com.projetovet.projeto_vet.service.PetPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/pet")
public class PetController {

	@Autowired
	private PetRepository repoP;
	
	@Autowired 
	private PetPaginacaoService paginacaoP;
	
	@Autowired 
	private PetCachingService cachingP;
	
	
	@Operation(
            summary = "Listar pets paginados",
            description = "Retorna pets em formato paginado com base em page e size",
            tags = "Retorno de informações do Pet")
	@GetMapping(value = "/paginados")
	public ResponseEntity<Page<PetDTO>> paginar(

	        @RequestParam(value = "page", defaultValue = "0") Integer page,
	        @RequestParam(value = "size", defaultValue = "2") Integer size) {

	    PageRequest req = PageRequest.of(page, size);

	    Page<PetDTO> paginados = paginacaoP.paginar(req);

	    return ResponseEntity.ok(paginados);
	}
	
	
	@Operation(
            summary = "Buscar pets por substring",
            description = "Busca pets filtrando por parte do nome ou outros campos via projection",
            tags = "Retorno de informações do Pet"
        )	
	@GetMapping(value="/substring")
	public List<PetProjection> retornarPorSubstring(@RequestParam String substring){
		
		return cachingP.retornarPetPorSubstring(substring);
	}
	
	
	
	@Operation(
            summary = "Buscar pets por nome",
            description = "Retorna lista de pets filtrados pelo nome",
            tags = "Retorno de informações do Pet"
        )
	@GetMapping(value="/por_nome")
	public List<Pet> retornarPorNome(@RequestParam String nome){
		
		return cachingP.retornarPetPorNome(nome);
	}
	
	@Operation(
            summary = "Buscar pet por raca",
            description = "Retorna lista de pets filtrados pela raca",
            tags = "Retorno de informações do Pet"
        )
	@GetMapping(value="/por_raca")
	public List<Pet> retornarPorRaca(@RequestParam String raca){
		
		return cachingP.retornarPetPorRaca(raca);
	}
	
	@Operation(
            summary = "Listar todos os pets (cache)",
            description = "Retorna todos os pets utilizando cache para performance",
            tags = "Retorno de informações do Pet"
        )
	@GetMapping(value="/todos")
	public List<Pet>retornarTodosPets(){
		return cachingP.findAll();
	}
	
	@Operation(
            summary = "Buscar pet por ID",
            description = "Retorna um pet específico pelo ID",
            tags = "Retorno de informações do Pet"
        )
	@GetMapping(value="/{id}")
	public Pet retornarPetPorId(@PathVariable @Valid Long id) {
		
		Optional<Pet> op = repoP.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@Operation(
            summary = "Criar novo pet",
            description = "Insere um novo pet no banco e limpa cache",
            tags = "Inserção de informações do Pet"
        )
	@PostMapping(value="/novo")
	public Pet inserirPet(@RequestBody @Valid Pet pet) {
		
		repoP.save(pet);
		cachingP.removerCache();
		
		return pet;
	}
	
	@Operation(
            summary = "Remover prt",
            description = "Remove um pet pelo ID e limpa cache",
            tags = "Remoção de informações do Pet"
        )
	@DeleteMapping(value="remover/{id}")
	public Pet deletarPet(@PathVariable @Valid Long id) {
		
		Optional<Pet> op = repoP.findById(id);
		
		
		if(op.isPresent()) {
			
			repoP.delete(op.get());
			cachingP.removerCache();
			
			return op.get();
			
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@Operation(
            summary = "Atualizar pet",
            description = "Atualiza dados de um pet existente",
            tags = "Atualização dos dados do Pet"
        )
	@PutMapping(value="atualizar/{id}")
	public Pet atualizarPet(@PathVariable Long id,@RequestBody @Valid Pet pet) {
		
		Optional<Pet> op = repoP.findById(id);
		
		if(op.isPresent()) {
			
			Pet petBanco = op.get();
			petBanco.transferirPet(pet);
			repoP.save(petBanco);
			cachingP.removerCache();
			return petBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

}
