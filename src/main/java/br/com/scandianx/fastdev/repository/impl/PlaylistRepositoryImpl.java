package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.Playlist;
import br.com.scandianx.fastdev.repository.interfaces.PlaylistRepository;

@Repository
public interface PlaylistRepositoryImpl 
        extends JpaRepository<Playlist, Long>, PlaylistRepository {
}
