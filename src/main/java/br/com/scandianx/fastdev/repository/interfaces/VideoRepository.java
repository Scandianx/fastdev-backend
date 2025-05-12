package br.com.scandianx.fastdev.repository.interfaces;

import java.util.Optional;

import br.com.scandianx.fastdev.model.VideoAbstrato;

public interface VideoRepository {
    VideoAbstrato save(VideoAbstrato video);
    Optional<VideoAbstrato> findById(Long id);
}
