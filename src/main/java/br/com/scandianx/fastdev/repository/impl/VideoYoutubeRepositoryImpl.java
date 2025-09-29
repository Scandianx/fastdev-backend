package br.com.scandianx.fastdev.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.scandianx.fastdev.entity.VideoYoutube;
import br.com.scandianx.fastdev.repository.interfaces.VideoYoutubeRepository;


@Repository
public interface VideoYoutubeRepositoryImpl extends JpaRepository<VideoYoutube, Long>, VideoYoutubeRepository{
    
}
