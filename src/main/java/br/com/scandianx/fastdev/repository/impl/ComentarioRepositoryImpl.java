package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.Comentario;
import br.com.scandianx.fastdev.repository.interfaces.ComentarioRepository;

import java.util.List;

@Repository
public interface ComentarioRepositoryImpl
        extends JpaRepository<Comentario, Long>, ComentarioRepository {

    @Override
    List<Comentario> findByVideoId(Long videoId);

    @Override
    @Query("select c from Comentario c where c.video.id = :videoId order by c.criadoEm desc")
    List<Comentario> findByVideoIdOrderByCriadoEmDesc(@Param("videoId") Long videoId);
}
