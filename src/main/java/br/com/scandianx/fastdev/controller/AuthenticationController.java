package br.com.scandianx.fastdev.controller;

import br.com.scandianx.fastdev.service.interfaces.AuthenticationService;
import br.com.scandianx.fastdev.view.AuthenticationDTO;
import br.com.scandianx.fastdev.view.LoginResponseDTO;
import br.com.scandianx.fastdev.view.RegisterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        return authenticationService.login(data);
    }

    @PostMapping("/register-visualizador")
    public ResponseEntity<String> registerVisualizador(@RequestBody RegisterDTO data) {
        return authenticationService.register(data);
    }
}
