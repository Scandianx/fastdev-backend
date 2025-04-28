package br.com.scandianx.fastdev.service.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.scandianx.fastdev.DTOs.AuthenticationDTO;
import br.com.scandianx.fastdev.DTOs.LoginResponseDTO;
import br.com.scandianx.fastdev.DTOs.RegisterDTO;

public interface AuthenticationService {
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data);
    public ResponseEntity<String> register(RegisterDTO data);

}
