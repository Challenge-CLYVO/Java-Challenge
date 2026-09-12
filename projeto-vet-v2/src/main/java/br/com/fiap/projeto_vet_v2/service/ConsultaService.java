package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_vet_v2.dto.ConsultaAgendamentoDTO;
import br.com.fiap.projeto_vet_v2.dto.ConsultaFinalizacaoDTO;
import br.com.fiap.projeto_vet_v2.model.Consulta;
import br.com.fiap.projeto_vet_v2.model.Lembrete;
import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Responsavel;
import br.com.fiap.projeto_vet_v2.model.Veterinario;
import br.com.fiap.projeto_vet_v2.repository.ConsultaRepository;
import br.com.fiap.projeto_vet_v2.repository.LembreteRepository;
import br.com.fiap.projeto_vet_v2.repository.PetRepository;
import br.com.fiap.projeto_vet_v2.repository.VeterinarioRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository repoC;
	
	@Autowired
	private VeterinarioRepository repoV;
	
	@Autowired
	private LembreteRepository repoL;
	
	@Autowired
	private PetRepository repoP;
	
	public List<Consulta> listTodos(){
		return repoC.findAll();
	}
	
	public Optional<Consulta> buscarPorId(Long id){
		return repoC.findById(id);
	}
	
	public List<Consulta> buscarPorPet(Pet pet){
		return repoC.findByPet(pet);
	}
	
	public List<Consulta> buscarPorVeterinario(Veterinario veterinario){
		return repoC.findByVeterinario(veterinario);
	}
	
	public Consulta salvar(Consulta consulta) {
		return repoC.save(consulta);
	}
	
	public void excluir(Long id) {
		repoC.deleteById(id);
	}
	
	@Transactional
	public Consulta agendarConsulta(ConsultaAgendamentoDTO dto, Responsavel responsavel) {
		
		Pet pet = repoP.findById(dto.getIdPet()).orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
		Veterinario veterinario = repoV.findById(dto.getIdVeterinario()).orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
		
		if (!pet.getResponsavel().getIdResponsavel().equals(responsavel.getIdResponsavel())) {
			
			throw new RuntimeException("Este pet não pertence ao responsáveç logado");
		}
		
	
	
		Consulta consulta  = new Consulta();
	
		consulta.setDataHora(dto.getDataHora());
		consulta.setMotivo(dto.getMotivo());
		consulta.setStatus("AGENDADA");
		consulta.setPet(pet);
		consulta.setVeterinario(veterinario);
		consulta.setClinica(veterinario.getClinica());
	
		consulta = repoC.save(consulta);
	
		Lembrete lembrete = new Lembrete();
	
		lembrete.setTitulo("Consulta agendada");
		lembrete.setDescricao("Consulta veterinária do pet" + pet.getNome());
		lembrete.setDataHora(dto.getDataHora());
		lembrete.setStatus("PENDENTE");
		lembrete.setPet(pet);
	
		repoL.save(lembrete);
	
		return consulta;
	}
	@Transactional
	public Consulta finalizarConsulta(Long idConsulta, ConsultaFinalizacaoDTO dto,Veterinario veterinario) {
		
		Consulta consulta = repoC.findById(idConsulta).orElseThrow(()-> new RuntimeException("Consulta não encontrada"));
		
		if(!consulta.getVeterinario().getIdVeterinario().equals(veterinario.getIdVeterinario())){
			
			throw new RuntimeException("Esta consulta não pertence ao veterinário logado");
		}
		
		consulta.setObservacao(dto.getObservacao());
		consulta.setStatus("REALIZADA");
		
		return repoC.save(consulta);
	}


}

	
	