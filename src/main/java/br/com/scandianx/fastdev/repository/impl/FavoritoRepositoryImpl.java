package br.com.scandianx.fastdev.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.Favorito;
import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.repository.interfaces.FavoritoRepository;


@Repository
public interface FavoritoRepositoryImpl 
        extends JpaRepository<Favorito, Long>, FavoritoRepository {
    @Override
    void deleteByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
    @Override
    boolean existsByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
    @Override
    List<Favorito> findByUsuario(Usuario usuario);
    @Override
    Optional<Favorito> findByUsuarioAndVideoId(Usuario usuario, Long videoId);
}
