package br.com.scandianx.fastdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.scandianx.fastdev.model.Usuario;
import br.com.scandianx.fastdev.model.VideoAbstrato;
import br.com.scandianx.fastdev.repository.impl.VideoRepositoryImpl;
import br.com.scandianx.fastdev.service.interfaces.AuthorizationService;
import br.com.scandianx.fastdev.service.interfaces.VideoService;
import br.com.scandianx.fastdev.view.VideoRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public VideoAbstrato criarVideo(@RequestBody VideoRequestDTO dto) {
        return videoService.criarVideo(dto);
    }

    @GetMapping
    public List<VideoAbstrato> listarPermitidos(HttpServletRequest request) {
        return videoService.listarPermitidos(request);
    }
}
