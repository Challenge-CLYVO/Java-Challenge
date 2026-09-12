package br.com.fiap.projeto_vet_v2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repoU;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public List<Usuario> listarTodos(){
		return repoU.findAll();
	}
	
	public Optional<Usuario> buscarPorId(Long id){
		return repoU.findById(id);
	}
	
	public Optional<Usuario> buscarPorEmail(String email){
		return repoU.findByEmail(email);
	}
	
	public Usuario salvar(Usuario usuario) {
		return repoU.save(usuario);
	}
	
	public void excluir(Long id) {
		repoU.deleteById(id);
	}

	public Usuario criarUsuario(
            String nome,
            String email,
            String senha,
            String telefone,
            String tipoUsuario) {

        if (repoU.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);

        usuario.setSenha(
                passwordEncoder.encode(senha)
        );

        usuario.setTelefone(telefone);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setStatus("ATIVO");
        usuario.setTipoUsuario(tipoUsuario);

        return repoU.save(usuario);
    }
	
}

