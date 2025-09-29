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
import br.com.scandianx.fastdev.dto.AuthenticationDTO;
import br.com.scandianx.fastdev.dto.LoginResponseDTO;
import br.com.scandianx.fastdev.dto.RegisterDTO;
import br.com.scandianx.fastdev.entity.UsuarioRole;
import br.com.scandianx.fastdev.entity.Visualizador;
import br.com.scandianx.fastdev.repository.interfaces.UsuarioRepository;
import br.com.scandianx.fastdev.repository.interfaces.VisualizadorRepository;
import br.com.scandianx.fastdev.security.TokenService;
import br.com.scandianx.fastdev.service.interfaces.AuthenticationService;

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
            
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
    
            var token = tokenService.generateToken((UserDetails) auth.getPrincipal());
    
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.badRequest().build();
        }
       
    }

    
    public ResponseEntity<String> register(RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.getLogin()) != null) return ResponseEntity.badRequest().body("Esse email já foi cadastrado no sistema!");

        String eP = new BCryptPasswordEncoder().encode(data.getPassword());
        Visualizador visualizador = new Visualizador();
        visualizador.setLogin(data.getLogin());
        visualizador.setNome(data.getNome());
        visualizador.setNomeCompleto(data.getNomeCompleto());
        visualizador.setPassword(eP);
        visualizador.setRole(UsuarioRole.USER);
        visualizador.setTelefone(data.getTelefone());
        observadores.forEach(obs -> obs.notificarNovoUsuario(visualizador));
        visualizadorRepository.save(visualizador);

        java.net.URI location = java.net.URI.create("/api/v1/usuarios/" + visualizador.getId());
        return ResponseEntity.created(location).body("Usuário cadastrado com sucesso!");
    }
   
}
