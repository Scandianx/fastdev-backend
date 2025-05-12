package br.com.scandianx.fastdev.repository.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.scandianx.fastdev.model.Favorito;
import br.com.scandianx.fastdev.model.Usuario;

public interface FavoritoRepository {
    Favorito save(Favorito favorito);
    void deleteByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
    boolean existsByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
    List<Favorito> findByUsuario(Usuario usuario);
    Optional<Favorito> findByUsuarioAndVideoId(Usuario usuario, Long videoId);
    void delete(Favorito fav);
}
