package br.com.projetovet.projeto_vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovet.projeto_vet.dto.TutorDTO;
import br.com.projetovet.projeto_vet.model.Tutor;
import br.com.projetovet.projeto_vet.repository.TutorRepository;



@Service
public class TutorPaginacaoService {

    @Autowired
    private TutorRepository repoT;

    @Transactional(readOnly = true)
    public Page<TutorDTO> paginar(PageRequest req){

        Page<Tutor> paginasTutor =
                repoT.findAll(req);

        Page<TutorDTO> paginasTutorDTO =
                paginasTutor.map(
                        tutor -> new TutorDTO(tutor));

        return paginasTutorDTO;
    }
}