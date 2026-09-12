package br.com.fiap.projeto_vet_v2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_vet_v2.model.Clinica;
import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.model.Veterinario;
import br.com.fiap.projeto_vet_v2.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired
	private VeterinarioRepository repoV;
	
	@Autowired
    private UsuarioService usuarioService;
	
	public List<Veterinario> listarTodos(){
		return repoV.findAll();
	}
	
	public Optional<Veterinario> buscarPorId(Long id ){
		return repoV.findById(id);
	}
	
	public Optional<Veterinario> buscarPorUsuario(Usuario usuario){
		return repoV.findByUsuario(usuario);
	}
	
	 public List<Veterinario> buscarPorClinica(Clinica clinica) {
	        return repoV.findByClinica(clinica);
	    }
	
	public Veterinario salvar(Veterinario veterinario) {
		return repoV.save(veterinario);
	}
	
	public void excluirVeterinario(Long id) {
		repoV.deleteById(id);
	}
	
	@Transactional
    public Veterinario cadastrar(
            String nome,
            String email,
            String senha,
            String telefone,
            String crv,
            String especialidade,
            Clinica clinica) {

        Usuario usuario = usuarioService.criarUsuario(
                nome,
                email,
                senha,
                telefone,
                "VETERINARIO"
        );

        Veterinario veterinario = new Veterinario();

        veterinario.setCrv(crv);
        veterinario.setEspecialidade(especialidade);
        veterinario.setClinica(clinica);
        veterinario.setUsuario(usuario);

        return repoV.save(veterinario);
    }
	
	
}
