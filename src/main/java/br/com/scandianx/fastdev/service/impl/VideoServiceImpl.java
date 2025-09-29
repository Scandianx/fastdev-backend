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
                .filter(video -> usuario != null && usuario.podeVisualizar(video.getNivelAcesso()))
                .toList();
    }
    public List<VideoAbstrato> listarTodos(){
        return videoRepository.findAll();
    }
}

