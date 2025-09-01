package br.com.scandianx.fastdev.service.interfaces;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import br.com.scandianx.fastdev.dto.VideoRequestDTO;
import br.com.scandianx.fastdev.entity.VideoAbstrato;

public interface VideoService {
    VideoAbstrato criarVideo(VideoRequestDTO dto);
    List<VideoAbstrato> listarPermitidos(HttpServletRequest request);
    List<VideoAbstrato> listarTodos();
}
