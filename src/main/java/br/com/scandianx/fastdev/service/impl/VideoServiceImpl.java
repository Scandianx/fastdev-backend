package br.com.scandianx.fastdev.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.scandianx.fastdev.components.VideoFactory;
import br.com.scandianx.fastdev.model.VideoAbstrato;

import br.com.scandianx.fastdev.service.interfaces.VideoService;
import br.com.scandianx.fastdev.view.VideoRequestDTO;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoFactory videoFactory;

    @Override
    public VideoAbstrato criarVideo(VideoRequestDTO dto) {
        return videoFactory.createAndSaveVideo(dto);
    }
}

