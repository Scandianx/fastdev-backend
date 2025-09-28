package br.com.scandianx.fastdev.service.impl;



import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.scandianx.fastdev.components.Converter;
import br.com.scandianx.fastdev.components.VideoFactory;
import br.com.scandianx.fastdev.dto.VideoDTO;
import br.com.scandianx.fastdev.dto.VideoRequestDTO;
import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.entity.VideoYoutube;
import br.com.scandianx.fastdev.repository.impl.VideoRepositoryImpl;
import br.com.scandianx.fastdev.service.interfaces.AuthorizationService;
import br.com.scandianx.fastdev.service.interfaces.VideoService;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoFactory videoFactory;

    @Autowired
    private VideoRepositoryImpl videoRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private Converter converter;

    @Override
    public VideoAbstrato criarVideo(VideoRequestDTO dto) {
        return videoFactory.createAndSaveVideo(dto);
    }
    @Override
    public List<VideoAbstrato> listarPermitidos(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        return videoRepository.findAll().stream()
                .filter(v -> usuario != null && usuario.podeVisualizar(v.getNivelAcesso()))
                .toList();
    }
    public List<VideoAbstrato> listarTodos(){
        return videoRepository.findAll();
    }

    @Override
    public List<VideoAbstrato> listarPermitidosPorTitulo(String q, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        return videoRepository.buscarPorTituloSimilar(q == null ? "" : q).stream()
                .filter(v -> usuario != null && usuario.podeVisualizar(v.getNivelAcesso()))
                .toList();
    }

    @Override
    public List<VideoAbstrato> listarMaisFavoritados(int limit, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        int lim = limit <= 0 ? 10 : limit;
        return videoRepository.buscarMaisFavoritados(lim).stream()
                .filter(v -> usuario != null && usuario.podeVisualizar(v.getNivelAcesso()))
                .toList();
    }
    
    @Override
    public VideoAbstrato atualizarVideo(Long id, VideoRequestDTO dto) {
        VideoAbstrato existente = videoRepository.findById(id)
            .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Vídeo não encontrado"));
        existente.setTitulo(dto.getTitulo());
        existente.setDescricao(dto.getDescricao());
        existente.setNivelAcesso(dto.getNivelAcesso());
        String tipo = dto.getTipo() == null ? "" : dto.getTipo().toLowerCase();
        if(!(existente instanceof VideoYoutube)){
            throw new IllegalArgumentException("Tipo de vídeo não suportado para atualização");
        }
        if(!"youtube".equals(tipo)){
            throw new IllegalArgumentException("Somente vídeos do tipo 'youtube' são suportados");
        }
        ((VideoYoutube) existente).setUrlYouTube(dto.getUrl());
        return videoRepository.save(existente);
    }

    @Override
    public void deletarVideo(Long id) {
        if(!videoRepository.existsById(id)){
            throw new jakarta.persistence.EntityNotFoundException("Vídeo não encontrado");
        }
        videoRepository.deleteById(id);
    }
    
    @Override
    public VideoAbstrato obterPorId(Long id) {
        return videoRepository.findById(id)
            .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Vídeo não encontrado"));
    }
    
}

