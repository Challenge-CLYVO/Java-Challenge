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

import br.com.projetovet.projeto_vet.dto.ConsultaDTO;
import br.com.projetovet.projeto_vet.model.Consulta;
import br.com.projetovet.projeto_vet.projection.ConsultaProjection;
import br.com.projetovet.projeto_vet.repository.ConsultaRepository;
import br.com.projetovet.projeto_vet.service.ConsultaCachingService;
import br.com.projetovet.projeto_vet.service.ConsultaPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaRepository repoC;

	@Autowired
	private ConsultaPaginacaoService paginacaoC;

	@Autowired
	private ConsultaCachingService cachingC;

	
	@Operation(
            summary = "Listar consultas paginados",
            description = "Retorna consulta em formato paginado com base em page e size",
            tags = "Retorno de informações da consulta"
        )
	@GetMapping
	public ResponseEntity<Page<ConsultaDTO>> paginar(

			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "2") Integer size) {

		PageRequest req = PageRequest.of(page, size);
		Page<ConsultaDTO> paginados = paginacaoC.paginar(req);

		return ResponseEntity.ok(paginados);
	}

	 @Operation(
	            summary = "Buscar consulta por substring",
	            description = "Busca consulta filtrando por parte do nome ou outros campos via projection",
	            tags = "Retorno de informações da consulta"
	        )
	@GetMapping("/substring")
	public List<ConsultaProjection> retornarConsultaPorSubstring(
			@RequestParam String substring) {

		return cachingC.retornarConsultaPorSubstring(substring);
	}

	 @Operation(
	            summary = "Buscar consulta por descrição",
	            description = "Retorna lista de consulta filtrados pela descrição",
	            tags = "Retorno de informações da consulta"
	        )
	@GetMapping("/por_descricao")
	public List<Consulta> retornarConsultaPorDescricao(
			@RequestParam String descricao) {

		return cachingC.retornarConsultaPorDescricao(descricao);
	}

	 @Operation(
	            summary = "Listar todas as consultas (cache)",
	            description = "Retorna todas as consultas utilizando cache para performance",
	            tags = "Retorno de informações da consulta"
	        )
	@GetMapping("/todas")
	public List<Consulta> retornarTodasConsultas() {

		return cachingC.findAll();
	}
	 
	 @Operation(
	            summary = "Buscar consulta por ID",
	            description = "Retorna uma consulta específica pelo ID",
	            tags = "Retorno de informações da consulta"
	        )
	@GetMapping("/{id}")
	public Consulta retornarConsultaPorId(@PathVariable Long id) {

		Optional<Consulta> op = cachingC.findById(id);

		if (op.isPresent()) {

			return op.get();

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	 @Operation(
	            summary = "Criar nova consulta",
	            description = "Insere uma nova consulta no banco e limpa cache",
	            tags = "Inserção de informações da consulta"
	        )
	@PostMapping("/novo")
	public Consulta inserirConsulta(@RequestBody Consulta consulta) {

		repoC.save(consulta);
		cachingC.removerCache();

		return consulta;
	}
	 

	 @Operation(
	            summary = "Remover consulta",
	            description = "Remove uma consulta pelo ID e limpa cache",
	            tags = "Remoção de informações da consulta"
	        )
	@DeleteMapping("/remover/{id}")
	public Consulta deletarConsulta(@PathVariable @Valid Long id) {

		Optional<Consulta> op = repoC.findById(id);

		if (op.isPresent()) {

			repoC.delete(op.get());
			cachingC.removerCache();

			return op.get();

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	 @Operation(
	            summary = "Atualizar consulta",
	            description = "Atualiza dados de uma consulta existente",
	            tags = "Atualização dos dados da consulta"
	        )
	@PutMapping("/atualizar/{id}")
	public Consulta atualizarConsulta(
			@PathVariable @Valid Long id,
			@RequestBody Consulta consulta) {

		Optional<Consulta> op = repoC.findById(id);

		if (op.isPresent()) {

			Consulta bancoConsulta = op.get();

			bancoConsulta.transferirConsulta(consulta);

			repoC.save(bancoConsulta);
			cachingC.removerCache();

			return bancoConsulta;

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}