package br.com.scandianx.fastdev.service.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.scandianx.fastdev.view.AuthenticationDTO;
import br.com.scandianx.fastdev.view.LoginResponseDTO;
import br.com.scandianx.fastdev.view.RegisterDTO;

public interface AuthenticationService {
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data);
    public ResponseEntity<String> register(RegisterDTO data);

}
