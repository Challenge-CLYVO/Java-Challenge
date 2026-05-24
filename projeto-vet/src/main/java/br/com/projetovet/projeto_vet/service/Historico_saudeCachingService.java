package br.com.projetovet.projeto_vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Historico_saude;
import br.com.projetovet.projeto_vet.projection.Historico_saudeProjection;
import br.com.projetovet.projeto_vet.repository.Historico_saudeRepository;

@Service
public class Historico_saudeCachingService {

    @Autowired
    private Historico_saudeRepository repoH;

    //paginacao
    @Cacheable(value = "retornarHistorico_saudePaginados", key = "#pr")
    public Page<Historico_saude> findAll(PageRequest pr) {
        
    	return repoH.findAll(pr);
    	
    }

    //substring
    @Cacheable(value = "retornarHistorico_saudePorSubstring", key = "#substring")
    public List<Historico_saudeProjection> retornarHistorico_saudePorSubstring(String substring) {
        
    	return repoH.retornarHistorico_saudePorSubString(substring);
    	
    }

    //por pet
    @Cacheable(value = "retornarHistorico_saudePorPet", key = "#pet")
    public List<Historico_saude> retornarHistorico_saudePorPet(String pet) {
        
    	return repoH.retornarHistorico_saudePorPet(pet);
    	
    }

    //todos
    @Cacheable(value = "retornarTodosHistoricos_saude")
    public List<Historico_saude> findAll() {
        
    	return repoH.findAll();
    }

    //por id
    @Cacheable(value = "retornarHistorico_saudePorId", key = "#id")
    public Optional<Historico_saude> findById(Long id) {
        
    	return repoH.findById(id);
    }
    
  
    //remover cache
    @CacheEvict(
        value = {
            "retornarHistorico_saudePaginados",
            "retornarHistorico_saudePorSubstring",
            "retornarHistorico_saudePorPet",
            "retornarTodosHistoricos_saude",
            "retornarHistorico_saudePorId"
        },
        allEntries = true
    )
    public void removerCache() {
        System.out.println("Cache removido!");
    }
}