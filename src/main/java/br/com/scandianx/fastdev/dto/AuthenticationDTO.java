package br.com.scandianx.fastdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationDTO(
        @NotBlank(message = "login obrigatório")
        @Size(max = 100, message = "login deve ter no máximo 100 caracteres")
        String login,

        @NotBlank(message = "senha obrigatória")
        @Size(min = 8, max = 72, message = "senha deve ter entre 8 e 72 caracteres")
        String password
) {}
