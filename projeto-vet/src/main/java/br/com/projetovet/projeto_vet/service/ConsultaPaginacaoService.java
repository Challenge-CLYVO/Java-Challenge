package br.com.projetovet.projeto_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.ConsultaDTO;
import br.com.projetovet.projeto_vet.model.Consulta;
import br.com.projetovet.projeto_vet.repository.ConsultaRepository;

@Service
public class ConsultaPaginacaoService {

	 @Autowired
	    private ConsultaRepository repoC;

	    @Transactional(readOnly = true)
	    public Page<ConsultaDTO> paginar(PageRequest req) {

	        Page<Consulta> paginasConsulta = repoC.findAll(req);

	        Page<ConsultaDTO> paginasConsultaDTO =
	                paginasConsulta.map(consulta -> new ConsultaDTO(consulta));

	        return paginasConsultaDTO;
	
	
      }
}