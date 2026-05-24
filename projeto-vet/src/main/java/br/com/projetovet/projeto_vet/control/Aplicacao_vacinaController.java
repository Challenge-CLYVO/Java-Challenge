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

import br.com.projetovet.projeto_vet.dto.Aplicacao_vacinaDTO;
import br.com.projetovet.projeto_vet.model.Aplicacao_vacina;
import br.com.projetovet.projeto_vet.projection.Aplicacao_vacinaProjection;
import br.com.projetovet.projeto_vet.repository.Aplicacao_vacinaRepository;
import br.com.projetovet.projeto_vet.service.Aplicacao_vacinaCachingService;
import br.com.projetovet.projeto_vet.service.Aplicacao_vacinaPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/aplicacao_vacina")
public class Aplicacao_vacinaController {

    @Autowired
    private Aplicacao_vacinaRepository repoA;

    @Autowired
    private Aplicacao_vacinaPaginacaoService paginacaoA;
    
    @Autowired
    private Aplicacao_vacinaCachingService cachingA;
    
   

    @Operation(
            summary = "Listar aplicações de vacina paginadas",
            description = "Retorna aplicações de vacina em formato paginado",
            tags = "retorno de informações da aplicacao Vacina"
    )
    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<Aplicacao_vacinaDTO>> paginar(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size) {

        PageRequest req = PageRequest.of(page, size);

        Page<Aplicacao_vacinaDTO> paginados = paginacaoA.paginar(req);

        return ResponseEntity.ok(paginados);
    }

    @Operation(
            summary = "Buscar aplicacao vacina por substring",
            description = "Busca aplicação de vacina por nome do pet/vacina via substring",
            tags = "retorno de informações da aplicacao vacina"
    )
    @GetMapping(value = "/substring")
    public List<Aplicacao_vacinaProjection> retornarAplicacao_vacinaPorSubstring(@RequestParam String substring) {
        
    	return cachingA.retornarAplicacao_vacinaPorSubstring(substring);
    	
    }

    @Operation(
            summary = "Buscar aplicacao vacina por pet",
            description = "Filtra aplicações de vacina por nome do pet",
            tags = "retorno de informações da aplicacao vacina"
    )
    @GetMapping(value = "/por_pet")
    public List<Aplicacao_vacina> retornarAplicacao_vacinaPorPet(@RequestParam String pet) {
        
    	return cachingA.retornarAplicacao_vacinaPorPet(pet);
    	
    }

    @Operation(
            summary = "Buscar aplicacao vacina por vacina",
            description = "Filtra aplicações de vacina por nome da vacina",
            tags = "retorno de informações da aplicacao vacina"
    )
    @GetMapping(value = "/por_vacina")
    public List<Aplicacao_vacina> retornarAplicacao_vacinaPorVacina(@RequestParam String vacina) {
       
    	return cachingA.retornarAplicacao_vacinaPorVacina(vacina);
    }

    @Operation(
            summary = "Listar todas aplicações de vacina",
            description = "Retorna todas aplicações com cache",
            tags = "retorno de informações da aplicacao vacina"
    )
    @GetMapping(value = "/todos")
    public List<Aplicacao_vacina> retornarTodasAplicacao_vacina() {
        
    	return cachingA.findAll();
    	
    }

    @Operation(
            summary = "Buscar aplicacao vacina por ID",
            description = "Retorna aplicação de vacina por ID",
            tags = "retorno de informações da aplicacao vacina"
    )
    @GetMapping(value = "/{id}")
    public Aplicacao_vacina retornarAplicacao_vacinaPorId(@PathVariable @Valid Long id) {

        Optional<Aplicacao_vacina> op = cachingA.findById(id);

        if (op.isPresent()) {
            return op.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Criar aplicação vacina ",
            description = "Insere nova aplicação de vacina e limpa cache",
            tags = "inserção de dados da aplicacao Vacina"
    )
    @PostMapping(value = "/novo")
    public Aplicacao_vacina inserirAplicacao_vacina(@RequestBody @Valid Aplicacao_vacina aplicacao_vacina) {

        repoA.save(aplicacao_vacina);
        cachingA.removerCache();

        return aplicacao_vacina;
    }

    @Operation(
            summary = "Deletar aplicação vacina",
            description = "Remove aplicação por ID",
            tags = "removoção de informações da aplicacao Vacina"
    )
    @DeleteMapping(value = "/remover/{id}")
    public Aplicacao_vacina deletarAplicacao_vacina(@PathVariable @Valid Long id) {

        Optional<Aplicacao_vacina> op = repoA.findById(id);

        if (op.isPresent()) {

            repoA.delete(op.get());
            cachingA.removerCache();

            return op.get();
        }else {

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    
       @Operation(
            summary = "Atualizar aplicação vacina",
            description = "Atualiza dados da aplicação de vacina",
            tags = "atualização de dados da aplicacao Vacina"
    )
    @PutMapping(value = "/atualizar/{id}")
    public Aplicacao_vacina atualizarAplicacao_vacina(@PathVariable Long id,
                                       @RequestBody @Valid Aplicacao_vacina aplicacao_vacina) {

        Optional<Aplicacao_vacina> op = repoA.findById(id);

        if(op.isPresent()) {
			
			Aplicacao_vacina aplicacao_vacinaBanco= op.get();
			aplicacao_vacinaBanco.transferirAplicacao_vacina(aplicacao_vacina);
			repoA.save(aplicacao_vacinaBanco);
			cachingA.removerCache();
			return aplicacao_vacinaBanco;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

    }
}