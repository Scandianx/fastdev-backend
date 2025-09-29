package br.com.scandianx.fastdev.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.dto.ComentarioDTO;
import br.com.scandianx.fastdev.dto.FavoritoDTO;
import br.com.scandianx.fastdev.dto.VideoCardDTO;
import br.com.scandianx.fastdev.dto.VideoDTO;
import br.com.scandianx.fastdev.entity.Comentario;
import br.com.scandianx.fastdev.entity.Favorito;
import br.com.scandianx.fastdev.entity.VideoAbstrato;

@Component
public class Converter {
    public FavoritoDTO toDTO(Favorito favorito) {
        return new FavoritoDTO(
                favorito.getVideo().getTitulo(),
                favorito.getFavoritadoEm());
    }

    public List<FavoritoDTO> toDTOList(List<Favorito> favoritos) {
        return favoritos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO toDTO(Comentario comentario) {
        return new ComentarioDTO(
                comentario.getId(),
                comentario.getTexto(),
                comentario.getUsuario().getNome(),
                comentario.getCriadoEm());
    }

    public List<ComentarioDTO> toDTOListComentarios(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VideoCardDTO toVideoCardDTO(VideoAbstrato v) {
        return new VideoCardDTO(
                v.getTitulo(),
                v.getUrl(),
                v.getNivelAcesso().name());
    }

    public List<VideoCardDTO> toVideoCardDTOList(List<VideoAbstrato> videos) {
        return videos.stream().map(this::toVideoCardDTO).collect(Collectors.toList());
    }

    public VideoDTO toDTO(VideoAbstrato video) {
        if (video == null)
            return null;

        return new VideoDTO(
                video.getId(),
                video.getTitulo(),
                video.getUrl(),
                video.getDescricao(),
                video.getDtCriacao(),
                video.getNivelAcesso().name());
    }

    public List<VideoDTO> toDTOListVideo(List<? extends VideoAbstrato> videos) {
        return videos.stream()
                .map(this::toDTO) 
                .collect(Collectors.toList());
    }

}
