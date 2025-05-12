package br.com.scandianx.fastdev.components;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.scandianx.fastdev.model.Comentario;
import br.com.scandianx.fastdev.model.Favorito;
import br.com.scandianx.fastdev.view.ComentarioDTO;
import br.com.scandianx.fastdev.view.FavoritoDTO;

@Component
public class Converter {
    public FavoritoDTO toDTO(Favorito favorito) {
        return new FavoritoDTO(
            favorito.getVideo().getTitulo(),
            favorito.getFavoritadoEm()
        );
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
            comentario.getCriadoEm()
        );
    }
    
    public List<ComentarioDTO> toDTOListComentarios(List<Comentario> comentarios) {
        return comentarios.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
}
