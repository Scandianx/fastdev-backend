package br.com.scandianx.fastdev.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.dto.VideoRequestDTO;
import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.entity.VideoYoutube;
import br.com.scandianx.fastdev.repository.interfaces.VideoYoutubeRepository;

@Component
public class VideoFactory {

    @Autowired
    private VideoYoutubeRepository youtubeRepo;

    public VideoAbstrato createAndSaveVideo(VideoRequestDTO dto) {
        String tipo = dto.getTipo().toLowerCase();
        
        if (tipo.equals("youtube")) {
            VideoYoutube v = new VideoYoutube();
            v.setUrlYouTube(dto.getUrl());
            v.setTitulo(dto.getTitulo());
            v.setNivelAcesso(dto.getNivelAcesso());
            return youtubeRepo.save(v);
        }

        throw new IllegalArgumentException("Tipo de vídeo inválido: " + tipo);
    }
}
