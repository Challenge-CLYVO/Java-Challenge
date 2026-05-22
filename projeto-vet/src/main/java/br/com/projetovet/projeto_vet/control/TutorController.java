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

import br.com.projetovet.projeto_vet.dto.TutorDTO;
import br.com.projetovet.projeto_vet.model.Tutor;
import br.com.projetovet.projeto_vet.projection.TutorProjection;
import br.com.projetovet.projeto_vet.repository.TutorRepository;
import br.com.projetovet.projeto_vet.service.TutorCachingService;
import br.com.projetovet.projeto_vet.service.TutorPaginacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tutor")
public class TutorController {

    @Autowired
    private TutorRepository repoT;

    @Autowired
    private TutorPaginacaoService paginacaoT;

    @Autowired
    private TutorCachingService cachingT;


    @Operation(
            summary = "Listar tutores paginados",
            description = "Retorna tutores em formato paginado com base em page e size",
            tags = "Retorno de informações do tutor"
        )
    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<TutorDTO>> paginar(

            @RequestParam(value = "page",
            defaultValue = "0") Integer page,

            @RequestParam(value = "size",
            defaultValue = "2") Integer size){

        PageRequest req = PageRequest.of(page, size);

        Page<TutorDTO> paginados =
                paginacaoT.paginar(req);

        return ResponseEntity.ok(paginados);
    }
    
    @Operation(
            summary = "Buscar tutores por substring",
            description = "Busca tutores filtrando por parte do nome ou outros campos via projection",
            tags = "Retorno de informações do tutor"
        )
    @GetMapping(value = "/substring")
    public List<TutorProjection> retornarTutorPorSubstring(
            @RequestParam String substring){

        return cachingT.retornarTutorPorSubstring(substring);
    }
    
    @Operation(
            summary = "Buscar tutores por nome",
            description = "Retorna lista de tutores filtrados pelo nome",
            tags = "Retorno de informações do tutor"
        )
    @GetMapping(value = "/por_nome")
    public List<Tutor> retornarTutorPorNome(
            @RequestParam String nome){

        return cachingT.retornarTutorPorNome(nome);
    }
    
    @Operation(
            summary = "Listar todos os tutores (cache)",
            description = "Retorna todos os tutores utilizando cache para performance",
            tags = "Retorno de informações do tutor"
        )
    @GetMapping(value = "/todos")
    public List<Tutor> retornarTodosTutores(){

        return cachingT.findAll();
    }

    @Operation(
            summary = "Buscar tutor por ID",
            description = "Retorna um tutor específico pelo ID",
            tags = "Retorno de informações do tutor"
        )
    @GetMapping(value = "/{id}")
    public Tutor retornarTutorPorId(
            @PathVariable Long id){

        Optional<Tutor> op =
                cachingT.findById(id);

        if(op.isPresent()){

            return op.get();

        } else {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @Operation(
            summary = "Criar novo tutor",
            description = "Insere um novo tutor no banco e limpa cache",
            tags = "Inserção de informações do tutor"
        )
    @PostMapping(value = "/novo")
    public Tutor inserirTutor(
            @RequestBody @Valid Tutor tutor){

        repoT.save(tutor);

        cachingT.removerCache();

        return tutor;
    }
    
    @Operation(
            summary = "Remover tutor",
            description = "Remove um tutor pelo ID e limpa cache",
            tags = "Remoção de informações do tutor"
        )
    @DeleteMapping(value = "/remover/{id}")
    public Tutor removerTutor(
            @PathVariable Long id){

        Optional<Tutor> op =
                repoT.findById(id);

        if(op.isPresent()){

            repoT.delete(op.get());

            cachingT.removerCache();

            return op.get();

        } else {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }
    }
    
    @Operation(
            summary = "Atualizar tutor",
            description = "Atualiza dados de um tutor existente",
            tags = "Atualização dos dados do tutor"
        )
    @PutMapping(value = "/atualizar/{id}")
    public Tutor atualizarTutor(

            @PathVariable Long id,

            @RequestBody @Valid Tutor tutor){

        Optional<Tutor> op =
                repoT.findById(id);

        if(op.isPresent()){

            Tutor tutorBanco = op.get();

            tutorBanco.transferirTutor(tutor);

            repoT.save(tutorBanco);

            cachingT.removerCache();

            return tutorBanco;

        } else {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }
    }
}