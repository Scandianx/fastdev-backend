package br.com.scandianx.fastdev.service.interfaces;

import java.util.List;

import br.com.scandianx.fastdev.dto.ComentarioDTO;
import jakarta.servlet.http.HttpServletRequest;



public interface ComentarioService {
    ComentarioDTO adicionarComentario(HttpServletRequest request, Long videoId, String texto);
    List<ComentarioDTO> listarComentariosPorVideo(Long videoId);
    void removerComentario(HttpServletRequest request, Long comentarioId);
}
