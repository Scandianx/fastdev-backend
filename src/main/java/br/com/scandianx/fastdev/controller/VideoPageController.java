package br.com.scandianx.fastdev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.scandianx.fastdev.components.Converter;
import br.com.scandianx.fastdev.dto.VideoCardDTO;
import br.com.scandianx.fastdev.dto.VideoDTO;
import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.service.interfaces.VideoService;

@Controller
@RequestMapping("/web")
public class VideoPageController {

    @GetMapping("/video")
    public String videoPage() {
        return "web/video";
    }

}
