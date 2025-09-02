package br.com.scandianx.fastdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record RegisterDTO(
        @NotBlank(message = "nome obrigatório")
        @Size(max = 60, message = "nome deve ter no máximo 60 caracteres")
        String nome,

        @NotBlank(message = "login obrigatório")
        @Size(max = 100, message = "login deve ter no máximo 100 caracteres")
        String login,

        @NotBlank(message = "senha obrigatória")
        @Size(min = 8, max = 72, message = "senha deve ter entre 8 e 72 caracteres")
        String password,

        @NotBlank(message = "nome completo obrigatório")
        @Size(max = 120, message = "nome completo deve ter no máximo 120 caracteres")
        String nomeCompleto,

        @NotBlank(message = "telefone obrigatório")
        @Pattern(regexp = "^[0-9()\\-\\s+]{10,20}$", message = "telefone inválido")
        String telefone
) {}
