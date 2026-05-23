package br.com.projetovet.projeto_vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Consulta;
import br.com.projetovet.projeto_vet.projection.ConsultaProjection;
import br.com.projetovet.projeto_vet.repository.ConsultaRepository;

@Service
public class ConsultaCachingService {

	@Autowired
	private ConsultaRepository repoC;
	
	//paginacao
	
	@Cacheable( 
			value="retornarConsultaPaginados",
			key="#pr"
			)
	public Page<Consulta>findAll(PageRequest pr){
		
		return repoC.findAll(pr);
	}
	
	//susbtring
	
	@Cacheable(
			value="retornarConsultaPorSubstring",
			key="#substring"
			)
	public List<ConsultaProjection>retornarConsultaPorSubstring(String substring){
		
		return repoC.retornarConsultaPorSubstring(substring);
	}
	
	//por descricao
	
	@Cacheable(
			value="retornarConsultaPorDescricao",
			key="#descricao"
			)
	public List<Consulta>retornarConsultaPorDescricao(String descricao){
		
		return repoC.retornarConsultaPorDescricao(descricao);
	}
	
	//todos
	
	@Cacheable( value="retornarTodasConsultas")
	public List<Consulta>findAll(){
		
		return repoC.findAll();
	}
		
	//por id
	@Cacheable(
			value="retornarConsultaPorId",
			key="#id"
			)
	public Optional<Consulta> findById(Long id){
		
		return repoC.findById(id);
	}
	
	//remover cache
	
	@CacheEvict(
            value = {
                    "retornarConsultasPaginados",
                    "retornarConsultaPorSubstring",
                    "retornarConsultaPorDescricao",
                    "retornarTodasConsultas",
                    "retornarConsultaPorId"
            },
            allEntries = true)
    public void removerCache(){

        System.out.println("Cache removido!");
    }
	
}
