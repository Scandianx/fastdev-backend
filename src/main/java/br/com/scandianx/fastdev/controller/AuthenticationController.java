package br.com.scandianx.fastdev.controller;

import br.com.scandianx.fastdev.dto.AuthenticationDTO;
import br.com.scandianx.fastdev.dto.LoginResponseDTO;
import br.com.scandianx.fastdev.dto.RegisterDTO;
import br.com.scandianx.fastdev.service.interfaces.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ProblemDetail;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Login e cadastro de usuários")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login de usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Invalid username supplied",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "422", description = "Regra de negócio violada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @jakarta.validation.Valid AuthenticationDTO data) {
        return authenticationService.login(data);
    }

    @PostMapping("/register-visualizador")
    @Operation(summary = "Cadastro de usuário visualizador")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "422", description = "Regra de negócio violada",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<String> registerVisualizador(@RequestBody @jakarta.validation.Valid RegisterDTO data) {
        ResponseEntity<String> resp = authenticationService.register(data);
        if (resp.getStatusCode().is2xxSuccessful()) {
            // Sem retorno de ID no service; mantém body e ajusta status para 201
            return ResponseEntity.status(201).body(resp.getBody());
        }
        return resp;
    }
}
