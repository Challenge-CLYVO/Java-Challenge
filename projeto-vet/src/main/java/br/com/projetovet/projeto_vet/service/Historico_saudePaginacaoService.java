package br.com.projetovet.projeto_vet.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.Historico_saudeDTO;
import br.com.projetovet.projeto_vet.model.Historico_saude;
import br.com.projetovet.projeto_vet.repository.Historico_saudeRepository;

@Service
public class Historico_saudePaginacaoService {

	@Autowired
	private Historico_saudeRepository repoH;
	
	@Transactional(readOnly=true)
	public Page<Historico_saudeDTO> paginar(PageRequest req){
		
		Page<Historico_saude> paginasHistorico_saude= repoH.findAll(req);
		Page<Historico_saudeDTO> paginasHistorico_saudeDTO= paginasHistorico_saude.map(historico_saude -> new Historico_saudeDTO(historico_saude));
		
		return paginasHistorico_saudeDTO;
	}
}
