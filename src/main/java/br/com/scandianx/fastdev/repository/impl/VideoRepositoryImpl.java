package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.repository.interfaces.VideoRepository;

@Repository
public interface VideoRepositoryImpl 
        extends JpaRepository<VideoAbstrato, Long>, VideoRepository {
}
