package br.com.projetovet.projeto_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.PetDTO;
import br.com.projetovet.projeto_vet.model.Pet;
import br.com.projetovet.projeto_vet.repository.PetRepository;

@Service
public class PetPaginacaoService {

	@Autowired
	private PetRepository repoP;
	
	@Transactional(readOnly= true)
	public Page<PetDTO> paginar (PageRequest req){
		
		
		Page<Pet> paginasPet =
                repoP.findAll(req);

        Page<PetDTO> paginasPetDTO =
                paginasPet.map(
                        pet -> new PetDTO(pet));

        return paginasPetDTO;
	}
	
	
}
