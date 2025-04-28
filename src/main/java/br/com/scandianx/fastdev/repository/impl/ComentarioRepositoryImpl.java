package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.model.Comentario;
import br.com.scandianx.fastdev.repository.interfaces.ComentarioRepository;

import java.util.List;

@Repository
public interface ComentarioRepositoryImpl 
        extends JpaRepository<Comentario, Long>, ComentarioRepository {
    @Override
    List<Comentario> findByVideoId(Long videoId);
}
