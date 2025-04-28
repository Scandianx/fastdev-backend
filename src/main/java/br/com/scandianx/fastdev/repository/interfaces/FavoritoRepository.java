package br.com.scandianx.fastdev.repository.interfaces;

import br.com.scandianx.fastdev.model.Favorito;

public interface FavoritoRepository {
    Favorito save(Favorito favorito);
    void deleteByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
    boolean existsByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
}
