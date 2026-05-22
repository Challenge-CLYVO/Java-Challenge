package br.com.projetovet.projeto_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.ClinicaDTO;
import br.com.projetovet.projeto_vet.model.Clinica;
import br.com.projetovet.projeto_vet.repository.ClinicaRepository;

@Service
public class ClinicaPaginacaoService {

	@Autowired
	private ClinicaRepository repoC;
	
	@Transactional(readOnly = true)
	public Page<ClinicaDTO> paginar (PageRequest req){
		
		
		Page<Clinica> paginasClinica =
                repoC.findAll(req);

        Page<ClinicaDTO> paginasClinicaDTO =
                paginasClinica.map(
                        pet -> new ClinicaDTO(pet));

        return paginasClinicaDTO;
		
		
		
	}
	
}
