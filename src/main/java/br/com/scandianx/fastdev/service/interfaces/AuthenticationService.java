package br.com.scandianx.fastdev.service.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.scandianx.fastdev.dto.AuthenticationDTO;
import br.com.scandianx.fastdev.dto.LoginResponseDTO;
import br.com.scandianx.fastdev.dto.RegisterDTO;

public interface AuthenticationService {
    public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data);
    public ResponseEntity<String> register(RegisterDTO data);

}
