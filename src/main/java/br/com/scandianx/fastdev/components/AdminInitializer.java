package br.com.scandianx.fastdev.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.model.UsuarioRole;
import br.com.scandianx.fastdev.model.Visualizador;
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
        if (usuarioRepository.findByLogin("admin") != null) return;

        String senhaCriptografada = new BCryptPasswordEncoder().encode("admin");

        Visualizador admin = new Visualizador();
        admin.setLogin("admin");
        admin.setNome("Filipe");
        admin.setNomeCompleto("Filipe Scandiani");
        admin.setPassword(senhaCriptografada);
        admin.setTelefone("28999060744");
        admin.setRole(UsuarioRole.ADMIN);

        
        visualizadorRepository.save(admin);
    }
}
