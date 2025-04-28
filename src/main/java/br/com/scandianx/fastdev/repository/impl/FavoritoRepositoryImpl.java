package br.com.scandianx.fastdev.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.model.Favorito;
import br.com.scandianx.fastdev.repository.impl.FavoritoRepository;


@Repository
public interface FavoritoRepositoryImpl 
        extends JpaRepository<Favorito, Long>, FavoritoRepository {
    @Override
    void deleteByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
    @Override
    boolean existsByUsuarioIdAndVideoId(Long usuarioId, Long videoId);
}
