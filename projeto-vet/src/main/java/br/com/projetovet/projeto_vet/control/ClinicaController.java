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

import br.com.projetovet.projeto_vet.dto.ClinicaDTO;
import br.com.projetovet.projeto_vet.model.Clinica;
import br.com.projetovet.projeto_vet.projection.ClinicaProjection;
import br.com.projetovet.projeto_vet.repository.ClinicaRepository;
import br.com.projetovet.projeto_vet.service.ClinicaCachingService;
import br.com.projetovet.projeto_vet.service.ClinicaPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clinica")
public class ClinicaController {

	@Autowired
	private ClinicaRepository repoC;
	
	@Autowired
	private ClinicaPaginacaoService paginacaoC;
	
	@Autowired
	private ClinicaCachingService cachingC;
	
	
	@Operation(
            summary = "Listar clinica paginados",
            description = "Retorna clinica em formato paginado com base em page e size",
            tags = "Retorno de informações da clinica")
	@GetMapping(value="/paginados")
	public ResponseEntity<Page<ClinicaDTO>> paginar(
			
			@RequestParam(value="page" ,defaultValue = "0") Integer page,
			
			@RequestParam(value="size", defaultValue="2") Integer size
			
			){
			
			PageRequest req = PageRequest.of(page,size);
			
			Page<ClinicaDTO> paginados = paginacaoC.paginar(req);
			
			return ResponseEntity.ok(paginados);
		
	}
	
	@Operation(
            summary = "Buscar clinica por substring",
            description = "Busca clinica filtrando por parte do nome ou outros campos via projection",
            tags = "Retorno de informações da clinica"
        )
	@GetMapping(value="/substring")
	public List<ClinicaProjection> retornarClinicaPorSubstring(@RequestParam String substring){
		
		return cachingC.retornarClinicaPorSubstring(substring);
		
	}
	
	@Operation(
            summary = "Buscar clinica por nome",
            description = "Retorna lista de clinica filtrado pelo nome",
            tags = "Retorno de informações da clinica"
        )
	@GetMapping(value="/por_nome")
	public List<Clinica> retornarClinicaPorNome(@RequestParam String nome){
		
		return cachingC.retornarClinicaPorNome(nome);
		
	}
	
	
	@Operation(
            summary = "Listar todas as clinicas (cache)",
            description = "Retorna todas as clinicas utilizando cache para performance",
            tags = "Retorno de informações das clinicas"
        )
	@GetMapping(value="/todas")
	private List<Clinica> retornarTodasClinicas(){
		return cachingC.findAll();
	}
	
	@Operation(
            summary = "Buscar clinica por ID",
            description = "Retorna uma clinica específica pelo ID",
            tags = "Retorno de informações da clinica"
        )
	@GetMapping(value="/{id}")
	private Clinica retornarClinicaPorId(@PathVariable @Valid Long id){
		
		Optional<Clinica> op = cachingC.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@Operation(
            summary = "Criar nova clinica",
            description = "Insere uma nova clinica no banco e limpa cache",
            tags = "Inserção de informações da clinica"
        )
	@PostMapping(value="/nova")
	private Clinica inserirClinica(@RequestBody @Valid Clinica clinica) {
		
		repoC.save(clinica);
		cachingC.removerCache();
		return clinica;
		
	}
	
	@DeleteMapping(value="remover/{id}")
	private Clinica removerClinica(@PathVariable @Valid Long id) {
		
		Optional<Clinica> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			repoC.delete(op.get());
			cachingC.removerCache();
			return op.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
			
		
		
	}
	
	
	@Operation(
            summary = "Remover clinica",
            description = "Remove uma clinica pelo ID e limpa cache",
            tags = "Remoção de informações da clinica"
        )
	@PutMapping(value="atualizar/{id}")
	private Clinica atualizarClinica(@PathVariable @Valid Long id ,@RequestBody Clinica clinica) {
		
		Optional<Clinica> op = repoC.findById(id);
		
		if(op.isPresent()) {
			
			Clinica clinicaBanco = op.get();
			clinicaBanco.transferirClinica(clinica);
			repoC.save(clinicaBanco);
			cachingC.removerCache();
			return clinicaBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
