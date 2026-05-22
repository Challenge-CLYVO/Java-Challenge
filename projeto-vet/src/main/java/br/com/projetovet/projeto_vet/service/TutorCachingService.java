package br.com.projetovet.projeto_vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Tutor;
import br.com.projetovet.projeto_vet.projection.TutorProjection;
import br.com.projetovet.projeto_vet.repository.TutorRepository;



@Service
public class TutorCachingService {

    @Autowired
    private TutorRepository repoT;

   
    // paginacao

    @Cacheable(
            value = "retornarTutoresPaginados",
            key = "#pr")
    public Page<Tutor> findAll(PageRequest pr){

        return repoT.findAll(pr);
    }

    // substring

    @Cacheable(
            value = "retornarTutorPorSubstring",
            key = "#substring")
    public List<TutorProjection>
    retornarTutorPorSubstring(String substring){

        return repoT.retornarTutorPorSubstring(substring);
    }

    //por nome
    @Cacheable(
            value = "retornarTutorPorNome",
            key = "#nome")
    public List<Tutor> retornarTutorPorNome(String nome){

        return repoT.retornarTutorPorNome(nome);
    }
    
    //todos 
    @Cacheable(value = "retornarTodosTutores")
    public List<Tutor> findAll(){

        return repoT.findAll();
    }
    
    //por id
    @Cacheable(
            value = "retornarTutorPorId",
            key = "#id")
    public Optional<Tutor> findById(Long id){

        return repoT.findById(id);
    }
    
    //remover cache
    @CacheEvict(
            value = {
                    "retornarTutoresPaginados",
                    "retornarTutorPorSubstring",
                    "retornarTutorPorNome",
                    "retornarTodosTutores",
                    "retornarTutorPorId"
            },
            allEntries = true)
    public void removerCache(){

        System.out.println("Cache removido!");
    }
}