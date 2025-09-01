package br.com.scandianx.fastdev.dto;

import java.time.LocalDateTime;

public record ComentarioDTO(Long id, String texto, String nomeUsuario, LocalDateTime criadoEm) {}
