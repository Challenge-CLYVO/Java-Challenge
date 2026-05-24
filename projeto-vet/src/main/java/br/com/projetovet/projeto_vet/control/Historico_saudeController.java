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

import br.com.projetovet.projeto_vet.dto.Historico_saudeDTO;
import br.com.projetovet.projeto_vet.model.Historico_saude;
import br.com.projetovet.projeto_vet.projection.Historico_saudeProjection;
import br.com.projetovet.projeto_vet.repository.Historico_saudeRepository;
import br.com.projetovet.projeto_vet.service.Historico_saudeCachingService;
import br.com.projetovet.projeto_vet.service.Historico_saudePaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/historico_saude")
public class Historico_saudeController {

    @Autowired
    private Historico_saudeRepository repoH;
    
    @Autowired
    private Historico_saudePaginacaoService paginacaoH;

    @Autowired
    private Historico_saudeCachingService cachingH;

    @Operation(
            summary = "Listar Historico Saude paginados",
            description = "Retorna Historico em formato paginado com base em page e size",
            tags = "Retorno de informações do Historico"
        )
    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<Historico_saudeDTO>> paginar(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size) {

        PageRequest req = PageRequest.of(page, size);
        
        Page<Historico_saudeDTO> paginados = paginacaoH.paginar(req);

        return ResponseEntity.ok(paginados);
    }

    @Operation(
            summary = "Buscar Historico por substring",
            description = "Busca Historico saude filtrando por parte do nome ou outros campos via projection",
            tags = "Retorno de informações do Historico"
        )
    @GetMapping(value = "/substring")
    public List<Historico_saudeProjection> retornarHistorico_saudePorsubstring(@RequestParam String substring) {
        
    	return cachingH.retornarHistorico_saudePorSubstring(substring);
    	
    }

    @Operation(
            summary = "Buscar Historico por pet",
            description = "Retorna lista de Historico Saude filtrados pelo pet",
            tags = "Retorno de informações do Historico"
        )
    @GetMapping(value = "/por_pet")
    public List<Historico_saude> retornarHistorico_saudePorPet(@RequestParam String pet) {
        
    	return cachingH.retornarHistorico_saudePorPet(pet);
        
    }

    @Operation(
            summary = "Listar todos os Historicos (cache)",
            description = "Retorna todos os Historicos saude utilizando cache para performance",
            tags = "Retorno de informações do Historico"
        )
    @GetMapping(value = "/todos")
    public List<Historico_saude> retornarTodosHistoricos_saude() {
        
    	return cachingH.findAll();
    	
    }

    @Operation(
            summary = "Buscar Historico por ID",
            description = "Retorna um Historico específico pelo ID",
            tags = "Retorno de informações do Historico"
        )
    @GetMapping(value = "/{id}")
    public Historico_saude retornarHistorico_saudePorId(@PathVariable @Valid Long id) {

        Optional<Historico_saude> op = cachingH.findById(id);

        if (op.isPresent()) {
            return op.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Criar novo Historico",
            description = "Insere um novo Historico no banco e limpa cache",
            tags = "Inserção de informações do Historico"
        )
    @PostMapping(value = "/novo")
    public Historico_saude inserirHistorico_saude(@RequestBody @Valid Historico_saude historico) {

        repoH.save(historico);
        cachingH.removerCache();

        return historico;
    }

    @Operation(
            summary = "Remover Historico",
            description = "Remove um Historico pelo ID e limpa cache",
            tags = "Remoção de informações do Historico"
        )
    @DeleteMapping(value = "/remover/{id}")
    public Historico_saude deletarHistorico_saude(@PathVariable @Valid Long id) {

        Optional<Historico_saude> op = repoH.findById(id);

        if (op.isPresent()) {

            repoH.delete(op.get());
            cachingH.removerCache();

            return op.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Atualizar Historico",
            description = "Atualiza dados de um Historico existente",
            tags = "Atualização dos dados do Historico"
        )
    @PutMapping(value = "/atualizar/{id}")
    public Historico_saude atualizarHistorico_saude(@PathVariable Long id, @RequestBody @Valid Historico_saude historico) {
                                      

        Optional<Historico_saude> op = repoH.findById(id);

        if (op.isPresent()) {

            Historico_saude banco = op.get();
            banco.transferirHistorico_saude(historico);

            repoH.save(banco);
            cachingH.removerCache();

            return banco;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}