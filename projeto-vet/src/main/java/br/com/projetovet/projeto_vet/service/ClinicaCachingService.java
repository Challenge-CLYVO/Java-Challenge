package br.com.projetovet.projeto_vet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Clinica;

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

    

   
}
