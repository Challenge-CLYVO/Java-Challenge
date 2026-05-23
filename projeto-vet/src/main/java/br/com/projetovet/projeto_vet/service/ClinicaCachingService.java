package br.com.projetovet.projeto_vet.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Clinica;
import br.com.projetovet.projeto_vet.projection.ClinicaProjection;
import br.com.projetovet.projeto_vet.repository.ClinicaRepository;

@Service
public class ClinicaCachingService {

	@Autowired
	private ClinicaRepository repoC;
	
	// paginação

    @Cacheable(
            value = "retornarClinicaPaginados",
            key = "#pr")
    public Page<Clinica> findAll(PageRequest pr){

        return repoC.findAll(pr);
    }

    //substring
    
 // substring

    @Cacheable(
        value = "retornarClinicaPorSubstring",
        key = "#substring"
    )
    public List<ClinicaProjection> retornarClinicaPorSubstring(String substring) {

        return repoC.retornarClinicaPorSubstring(substring);
    }
    
    
    //por nome
    
    @Cacheable(
    		value ="retornarClinicaPorNome",
    		key ="#nome"
    		)
    public List<Clinica> retornarClinicaPorNome(String nome){
    	
    	return repoC.retornarClinicaPorNome(nome);
    	
    }
    
    //todos
    
    @Cacheable(value="retornarTodosClinica")
    public List<Clinica> findAll(){
    	
    	return repoC.findAll();
    }
    
    
    //por id
    
    @Cacheable(
    		value= "retornarClinicaPorId",
    		key="#id"
    		)
    public Optional<Clinica> findById(Long id){
    	
    	return repoC.findById(id);
    	
    }
    
    //remover cache
    @CacheEvict(
    		value= {
    				"retornarClinicaPaginados",
    				"retornarClinicaPorSubstring",
    				"retornarClinicaPorNome",
    				"retornarClinicaTutores",
    				"retornarClinicaPorId"
    		},
    		allEntries = true)
    public void removerCache() {
    	
    	System.out.println("Cache removido!");
    }
   
}

