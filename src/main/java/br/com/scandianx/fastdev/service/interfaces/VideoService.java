package br.com.scandianx.fastdev.service.interfaces;

import br.com.scandianx.fastdev.view.VideoRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import br.com.scandianx.fastdev.model.VideoAbstrato;

public interface VideoService {
    VideoAbstrato criarVideo(VideoRequestDTO dto);
    List<VideoAbstrato> listarPermitidos(HttpServletRequest request);
}
