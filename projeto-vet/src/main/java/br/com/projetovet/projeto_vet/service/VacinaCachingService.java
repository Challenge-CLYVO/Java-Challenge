package br.com.projetovet.projeto_vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.projetovet.projeto_vet.model.Vacina;
import br.com.projetovet.projeto_vet.projection.VacinaProjection;
import br.com.projetovet.projeto_vet.repository.VacinaRepository;

@Service
public class VacinaCachingService {

	@Autowired
	private VacinaRepository repoV;
	
	//paginacao
	@Cacheable(
			value="retornarVacinaPaginados",
			key="#pr"
			)
	public Page<Vacina>findAll(PageRequest pr){
		
		return repoV.findAll(pr);
	}
	//substring
	@Cacheable(
			value="retornarVacinaPorSubstring",
			key="#substring"
			)
	public List<VacinaProjection>retornarVacinaPorSubstring(String substring){
		
		return repoV.retornarVacinaPorSubstring(substring);
	}
	//por nome
	@Cacheable(
			value="retornarVacinaPorNome",
			key="#nome"
			)
	public List<Vacina> retornarVacinaPorNome(String nome){
		
		return repoV.retornarVacinaPorNome(nome);
	}
	//todos
	@Cacheable( value="retornarTodasVacinas")
	public List<Vacina>findAll(){
		
		return repoV.findAll();
	}		
	//por id
	@Cacheable(
			value="retornarVacinaPorId",
			key="#id"
			)
	public Optional<Vacina> findById(Long id){
		
		return repoV.findById(id);
	}
	
	//remover cache
	@CacheEvict(
		value= {
				"retornarVacinaPaginados",
				"retornarVacinaPorSubstring",
				"retornarVacinaPorNome",
				"retornarTodasVacinas",
				"retornarVacinaPorId"
		},
		allEntries = true
			)
	public void removerCache() {
		
		System.out.println("Cache removido");
	}
}
