package br.com.scandianx.fastdev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scandianx.fastdev.components.Converter;
import br.com.scandianx.fastdev.dto.ComentarioDTO;
import br.com.scandianx.fastdev.entity.Comentario;
import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.repository.interfaces.ComentarioRepository;
import br.com.scandianx.fastdev.repository.interfaces.VideoRepository;
import br.com.scandianx.fastdev.service.interfaces.AuthorizationService;
import br.com.scandianx.fastdev.service.interfaces.ComentarioService;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private Converter converter;

    @Override
    public ComentarioDTO adicionarComentario(HttpServletRequest request, Long videoId, String texto) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        Optional<VideoAbstrato> videoOpt = videoRepository.findById(videoId);
        if (usuario == null || videoOpt.isEmpty()) {
            return null;
        }
        Comentario comentario = new Comentario();
        comentario.setTexto(texto);
        comentario.setUsuario(usuario);
        comentario.setVideo(videoOpt.get());
        Comentario salvo = comentarioRepository.save(comentario);
        return converter.toDTO(salvo);
    }

    @Override
    public List<ComentarioDTO> listarComentariosPorVideo(Long videoId) {
        List<Comentario> comentarios = comentarioRepository.findByVideoId(videoId);
        return converter.toDTOListComentarios(comentarios);
    }

    @Override
    public void removerComentario(HttpServletRequest request, Long comentarioId) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        Optional<Comentario> comentario = comentarioRepository.findById(comentarioId);
        if (comentario.isEmpty()){
            return;
        }
        if (comentario.get().getUsuario().getId()!= usuario.getId()) {
            return;
        }
        if (usuario != null) {
            comentarioRepository.delete(comentario.get());
        }
    }

    @Override
    public ComentarioDTO atualizarComentario(HttpServletRequest request, Long comentarioId, String novoTexto) {
        String token = request.getHeader("Authorization");
        Usuario usuario = authorizationService.findUserByToken(token);
        if (usuario == null) {
            return null;
        }

        Optional<Comentario> comentarioOpt = comentarioRepository.findById(comentarioId);
        if (comentarioOpt.isEmpty()) {
            return null;
        }

        Comentario comentario = comentarioOpt.get();
        if (!comentario.getUsuario().getId().equals(usuario.getId())) {
            return null;
        }

        comentario.setTexto(novoTexto);
        Comentario salvo = comentarioRepository.save(comentario);
        return converter.toDTO(salvo);
    }
}
