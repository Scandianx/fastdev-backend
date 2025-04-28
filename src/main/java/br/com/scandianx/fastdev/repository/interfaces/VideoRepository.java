package br.com.scandianx.fastdev.repository.interfaces;

import br.com.scandianx.fastdev.model.VideoAbstrato;

public interface VideoRepository {
    VideoAbstrato save(VideoAbstrato video);
}
