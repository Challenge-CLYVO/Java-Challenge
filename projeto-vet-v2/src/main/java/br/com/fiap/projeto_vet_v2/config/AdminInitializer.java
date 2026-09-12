package br.com.fiap.projeto_vet_v2.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.fiap.projeto_vet_v2.model.Usuario;
import br.com.fiap.projeto_vet_v2.repository.UsuarioRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository repoU;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String emailAdmin = "admin@petcare.com";

        if (repoU.findByEmail(emailAdmin).isEmpty()) {

            Usuario admin = new Usuario();

            admin.setNome("Administrador");
            admin.setEmail(emailAdmin);
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setTelefone("11999999999");
            admin.setDataCadastro(LocalDateTime.now());
            admin.setStatus("ATIVO");
            admin.setTipoUsuario("ADMIN");

            repoU.save(admin);

            System.out.println("ADMIN criado com sucesso.");
        }
    }
}
