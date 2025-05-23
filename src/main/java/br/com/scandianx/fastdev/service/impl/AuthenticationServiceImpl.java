package br.com.scandianx.fastdev.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.scandianx.fastdev.components.UsuarioObserver;
import br.com.scandianx.fastdev.model.UsuarioRole;
import br.com.scandianx.fastdev.model.Visualizador;
import br.com.scandianx.fastdev.repository.interfaces.UsuarioRepository;
import br.com.scandianx.fastdev.repository.interfaces.VisualizadorRepository;
import br.com.scandianx.fastdev.security.TokenService;
import br.com.scandianx.fastdev.service.interfaces.AuthenticationService;
import br.com.scandianx.fastdev.view.AuthenticationDTO;
import br.com.scandianx.fastdev.view.LoginResponseDTO;
import br.com.scandianx.fastdev.view.RegisterDTO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private VisualizadorRepository visualizadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private List<UsuarioObserver> observadores;

    
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data){
        try {
            
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
    
            var token = tokenService.generateToken((UserDetails) auth.getPrincipal());
    
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.badRequest().build();
        }
       
    }

    
    public ResponseEntity<String> register(RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().body("Esse email já foi cadastrado no sistema!");

        String eP = new BCryptPasswordEncoder().encode(data.password());
        Visualizador visualizador = new Visualizador();
        visualizador.setLogin(data.login());
        visualizador.setNome(data.nome());
        visualizador.setNomeCompleto(data.nomeCompleto());
        visualizador.setPassword(eP);
        visualizador.setRole(UsuarioRole.USER);
        visualizador.setTelefone(data.telefone());
        observadores.forEach(obs -> obs.notificarNovoUsuario(visualizador));
        visualizadorRepository.save(visualizador);

        return ResponseEntity.ok().body("Você foi cadastrado com sucesso!");
    }
   
}
