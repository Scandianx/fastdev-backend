package br.com.scandianx.fastdev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scandianx.fastdev.components.Converter;
import br.com.scandianx.fastdev.model.Favorito;
import br.com.scandianx.fastdev.model.Usuario;
import br.com.scandianx.fastdev.model.VideoAbstrato;
import br.com.scandianx.fastdev.repository.interfaces.FavoritoRepository;
import br.com.scandianx.fastdev.repository.interfaces.VideoRepository;
import br.com.scandianx.fastdev.service.interfaces.AuthorizationService;
import br.com.scandianx.fastdev.service.interfaces.FavoritosService;
import br.com.scandianx.fastdev.view.FavoritoDTO;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class FavoritosServiceImpl implements FavoritosService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private Converter converter;

    @Override
    public FavoritoDTO adicionarFavorito(HttpServletRequest request, Long videoId) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        Optional<VideoAbstrato> videoOpt = videoRepository.findById(videoId);
        if (usuario == null || videoOpt.isEmpty()) {
            return null;
        }
        Favorito favorito = new Favorito(usuario, videoOpt.get());
        return converter.toDTO(favoritoRepository.save(favorito));
    }

    @Override
    public List<FavoritoDTO> listarPorUsuario(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        if (usuario == null) {
            return List.of();
        }
        List<Favorito> favoritos = favoritoRepository.findByUsuario(usuario);
        return converter.toDTOList(favoritos);
    }

    @Override
    public void removerFavorito(HttpServletRequest request, Long videoId) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        if (usuario == null) {
            return;
        }
        Optional<Favorito> favOpt = favoritoRepository.findByUsuarioAndVideoId(usuario, videoId);
        if (favOpt.isPresent()) {
            Favorito fav = favOpt.get();
            favoritoRepository.delete(fav);
        }
    }
}
