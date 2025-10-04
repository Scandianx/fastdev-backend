package br.com.scandianx.fastdev.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.Favorito;
import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.repository.interfaces.FavoritoRepository;

@Repository
public interface FavoritoRepositoryImpl
        extends JpaRepository<Favorito, Long>, FavoritoRepository {

    @Override
    boolean existsByUsuarioIdAndVideoId(Long usuarioId, Long videoId);

    @Query(value = "SELECT * FROM favoritos f WHERE f.usuario_id = :usuarioId", nativeQuery = true)
    List<Favorito> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query(value = "SELECT * FROM favoritos f WHERE f.usuario_id = :usuarioId AND f.video_id = :videoId", nativeQuery = true)
    Optional<Favorito> findByUsuarioAndVideoId(@Param("usuarioId") Long usuarioId, @Param("videoId") Long videoId);

    @Modifying
    @Query(value = "DELETE FROM favoritos WHERE usuario_id = :usuarioId AND video_id = :videoId", nativeQuery = true)
    void deleteByUsuarioIdAndVideoId(@Param("usuarioId") Long usuarioId, @Param("videoId") Long videoId);
}
