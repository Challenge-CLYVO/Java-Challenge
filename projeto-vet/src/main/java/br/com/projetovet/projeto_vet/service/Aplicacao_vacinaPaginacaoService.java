package br.com.projetovet.projeto_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.Aplicacao_vacinaDTO;
import br.com.projetovet.projeto_vet.model.Aplicacao_vacina;
import br.com.projetovet.projeto_vet.repository.Aplicacao_vacinaRepository;

@Service
public class Aplicacao_vacinaPaginacaoService {

	@Autowired
	private Aplicacao_vacinaRepository repoA;
	
	@Transactional(readOnly= true)
	public Page<Aplicacao_vacinaDTO>paginar(PageRequest req){
		
		Page<Aplicacao_vacina>paginasAplicacao_vacina = repoA.findAll(req);
		Page<Aplicacao_vacinaDTO> paginasAplicacao_vacinaDTO = paginasAplicacao_vacina.map(aplicacao_vacina -> new Aplicacao_vacinaDTO(aplicacao_vacina));
				
		return paginasAplicacao_vacinaDTO;		
		
	}
}
