package br.com.scandianx.fastdev.repository.interfaces;

import br.com.scandianx.fastdev.model.Comentario;
import java.util.List;

public interface ComentarioRepository {
    Comentario save(Comentario comentario);
    List<Comentario> findByVideoId(Long videoId);
}
