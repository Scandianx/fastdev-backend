package br.com.scandianx.fastdev.view;

import java.time.LocalDateTime;

public record ComentarioDTO(Long id, String texto, String nomeUsuario, LocalDateTime criadoEm) {}
