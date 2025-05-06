package br.com.scandianx.fastdev.service.interfaces;

import br.com.scandianx.fastdev.view.VideoRequestDTO;
import br.com.scandianx.fastdev.model.VideoAbstrato;

public interface VideoService {
    VideoAbstrato criarVideo(VideoRequestDTO dto);
}
