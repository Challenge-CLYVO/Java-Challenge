package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_vet_v2.dto.PetFormDTO;
import br.com.fiap.projeto_vet_v2.model.Pet;
import br.com.fiap.projeto_vet_v2.model.Responsavel;
import br.com.fiap.projeto_vet_v2.model.Sensor;
import br.com.fiap.projeto_vet_v2.repository.PetRepository;
import br.com.fiap.projeto_vet_v2.repository.SensorRepository;

@Service
public class PetService {

	@Autowired
	PetRepository repoP;
	
	@Autowired
	private SensorRepository repoS;
	
	public List<Pet> listarTodos(){
		return repoP.findAll();
	}
	
	public Optional<Pet> buscarPorId(Long id){
		return repoP.findById(id);
	}
	
	public List <Pet> buscarPorResponsavel(Responsavel responsavel){
		return repoP.findByResponsavel(responsavel);
	}
	
	public Pet salvar(Pet pet) {
		return repoP.save(pet);
	}
	
	public void excluir(Long id) {
		repoP.deleteById(id);
	}
	
	@Transactional
	public Pet cadastrar(PetFormDTO dto, Responsavel responsavel) {

	    Pet pet = new Pet();

	    pet.setNome(dto.getNome());
	    pet.setSexo(dto.getSexo());
	    pet.setRaca(dto.getRaca());
	    pet.setEspecie(dto.getEspecie());
	    pet.setDataNascimento(dto.getDataNascimento());
	    pet.setResponsavel(responsavel);

	    pet = repoP.save(pet);


	    Sensor temperatura = new Sensor();

	    temperatura.setTipo("TEMPERATURA");
	    temperatura.setUnidade("C");
	    temperatura.setStatus("ATIVO");
	    temperatura.setPet(pet);

	    repoS.save(temperatura);


	    Sensor atividade = new Sensor();

	    atividade.setTipo("ATIVIDADE");
	    atividade.setUnidade("METROS");
	    atividade.setStatus("ATIVO");
	    atividade.setPet(pet);

	    repoS.save(atividade);


	    return pet;
	}
}
