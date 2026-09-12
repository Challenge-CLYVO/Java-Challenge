package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_vet_v2.dto.UsuarioCadastroDTO;
import br.com.fiap.projeto_vet_v2.model.Responsavel;
import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.repository.ResponsavelRepository;
import br.com.fiap.projeto_vet_v2.repository.UsuarioRepository;

@Service
public class ResponsavelService {

	@Autowired
	private ResponsavelRepository repoR;
	
	@Autowired
    private UsuarioService usuarioService;

	
	public List<Responsavel> listarTodos(){
		return repoR.findAll();
	}
	
	public Optional<Responsavel> buscarPorId(Long id){
		return repoR.findById(id);
	}
	
	public Optional<Responsavel> buscarPorUsuario(Usuario usuario){
		return repoR.findByUsuario(usuario);
	}
	
	public Responsavel salvar (Responsavel responsavel) {
		return repoR.save(responsavel);
	}
	
	@Transactional
	public Responsavel cadastrar(UsuarioCadastroDTO dto) {
		
		if(!dto.getSenha().equals(dto.getConfirmarSenha())) {
			throw new RuntimeException("As senhas não coicidem");
		}
	
		Usuario usuario = usuarioService.criarUsuario(
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getTelefone(),
                "RESPONSAVEL"
        );
	    
	    Responsavel responsavel = new Responsavel();

	    responsavel.setCpf(dto.getCpf());
	    responsavel.setDataNascimento(dto.getDataNascimento());
	    responsavel.setUsuario(usuario);

	    return repoR.save(responsavel);
	
	}
	}
