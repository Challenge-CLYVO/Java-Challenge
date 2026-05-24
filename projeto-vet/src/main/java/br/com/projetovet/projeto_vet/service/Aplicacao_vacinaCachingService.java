package br.com.projetovet.projeto_vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Aplicacao_vacina;
import br.com.projetovet.projeto_vet.projection.Aplicacao_vacinaProjection;
import br.com.projetovet.projeto_vet.repository.Aplicacao_vacinaRepository;

@Service
public class Aplicacao_vacinaCachingService {

    @Autowired
    private Aplicacao_vacinaRepository repoA;

    // paginação
    @Cacheable(value = "retornarAplicacao_vacinaPaginados", key = "#pr")
    public Page<Aplicacao_vacina> findAll(PageRequest pr) {
        
    	 return repoA.findAll(pr);
    	 
    }

    // substring
    @Cacheable(value = "retornarAplicacao_vacinaPorSubstring", key = "#substring")
    public List<Aplicacao_vacinaProjection> retornarAplicacao_vacinaPorSubstring(String substring) {
        
    	return repoA.retornarAplicacao_vacinaPorSubstring(substring);
    	
    }

    // por pet
    @Cacheable(value = "retornarAplicacao_vacinaPorPet", key = "#pet")
    public List<Aplicacao_vacina> retornarAplicacao_vacinaPorPet(String pet) {
       
    	return repoA.retornarAplicacao_vacinaPorPet(pet);
    	
    }

    // por vacina
    @Cacheable(value = "retornarAplicacao_vacinaPorVacina", key = "#vacina")
    public List<Aplicacao_vacina> retornarAplicacao_vacinaPorVacina(String vacina) {
        
    	return repoA.retornarAplicacao_vacinaPorVacina(vacina);
    	
    }

    // todos
    @Cacheable(value = "retornarTodasAplicacao_vacina")
    public List<Aplicacao_vacina> findAll() {
        
    	return repoA.findAll();
    	
    }

    // por id
    @Cacheable(value = "retornarAplicacao_vacinaPorId", key = "#id")
    public Optional<Aplicacao_vacina> findById(Long id) {
        
    	return repoA.findById(id);
    	
    }

    // limpar cache
    @CacheEvict(
        value = {
            "retornarAplicacao_vacinaPaginados",
            "retornarAplicacao_vacinaPorSubstring",
            "retornarAplicacao_vacinaPorPet",
            "retornarAplicacao_vacinaPorVacina",
            "retornarTodasAplicacao_vacina",
            "retornarAplicacao_vacinaPorId"
        },
        allEntries = true
    )
    public void removerCache() {
        System.out.println("Cache removido");
    }
}