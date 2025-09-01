package br.com.scandianx.fastdev.repository.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.scandianx.fastdev.entity.Comentario;

public interface ComentarioRepository {
    Comentario save(Comentario comentario);
    List<Comentario> findByVideoId(Long videoId);
    Optional<Comentario> findById(Long id);
    void delete(Comentario comentario);
}
