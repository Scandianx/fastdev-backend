package br.com.scandianx.fastdev.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.entity.UsuarioRole;
import br.com.scandianx.fastdev.entity.Visualizador;
import br.com.scandianx.fastdev.repository.interfaces.UsuarioRepository;
import br.com.scandianx.fastdev.repository.interfaces.VisualizadorRepository;
import jakarta.annotation.PostConstruct;

@Component
public class AdminInitializer {
    @Autowired
    private VisualizadorRepository visualizadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void criarAdminSeNaoExistir() {
        if (usuarioRepository.findByLogin("admin@admin") == null) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode("admin@admin");
            Visualizador admin = new Visualizador();
            admin.setLogin("admin@admin");
            admin.setNome("Filipe");
            admin.setNomeCompleto("Filipe Scandiani");
            admin.setPassword(senhaCriptografada);
            admin.setTelefone("28999060744");
            admin.setRole(UsuarioRole.ADMIN);
            visualizadorRepository.save(admin);
        }
        if (usuarioRepository.findByLogin("scandianidev@gmail.com") == null) {
            String senhaCriptografada = new BCryptPasswordEncoder().encode("scandianidev@gmail.com");
            Visualizador admin2 = new Visualizador();
            admin2.setLogin("scandianidev@gmail.com");
            admin2.setNome("Scandiani");
            admin2.setNomeCompleto("Scandiani Dev");
            admin2.setPassword(senhaCriptografada);
            admin2.setTelefone("0000000000");
            admin2.setRole(UsuarioRole.USDEV);
            visualizadorRepository.save(admin2);
        }
    }
}

