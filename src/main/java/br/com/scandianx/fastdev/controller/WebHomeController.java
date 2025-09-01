package br.com.scandianx.fastdev.controller;

import br.com.scandianx.fastdev.components.Converter;

import br.com.scandianx.fastdev.dto.VideoCardDTO;
import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.service.interfaces.VideoService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WebHomeController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private Converter converterComponent;

    @GetMapping("/web")
    public String home(HttpServletRequest request, Model model) {
        List<VideoCardDTO> videos = converterComponent.toVideoCardDTOList(videoService.listarTodos());
        System.out.println("videos zie " + videos.size());
        model.addAttribute("videos", videos);
        return "web/home";
    }

}
