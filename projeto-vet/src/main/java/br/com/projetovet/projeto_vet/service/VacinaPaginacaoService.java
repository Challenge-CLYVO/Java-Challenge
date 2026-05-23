package br.com.projetovet.projeto_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.VacinaDTO;
import br.com.projetovet.projeto_vet.model.Vacina;
import br.com.projetovet.projeto_vet.repository.VacinaRepository;
;

@Service
public class VacinaPaginacaoService {

	@Autowired
	private VacinaRepository repoC;
	
	@Transactional(readOnly= true)
	public Page<VacinaDTO> paginar(PageRequest req){
		
		
		Page<Vacina>paginasVacina=
				repoC.findAll(req);
		Page<VacinaDTO>paginasVacinaDTO=
				paginasVacina.map(
						vacina -> new VacinaDTO(vacina));
				
		return paginasVacinaDTO;
	}
	
}
