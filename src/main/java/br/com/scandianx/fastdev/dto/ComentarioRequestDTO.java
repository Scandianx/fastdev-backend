package br.com.scandianx.fastdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComentarioRequestDTO(
        @NotBlank(message = "texto obrigatório")
        @Size(max = 500, message = "texto deve ter no máximo 500 caracteres")
        String texto
) {}
