package br.com.scandianx.fastdev.service.interfaces;

import java.util.List;


import br.com.scandianx.fastdev.view.FavoritoDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface FavoritosService {
    FavoritoDTO adicionarFavorito(HttpServletRequest request, Long videoId);
    List<FavoritoDTO> listarPorUsuario(HttpServletRequest request);
    void removerFavorito(HttpServletRequest request, Long videoId);
}
