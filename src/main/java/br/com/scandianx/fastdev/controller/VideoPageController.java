package br.com.scandianx.fastdev.controller;



import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class VideoPageController {

   @GetMapping("/video")
    public String videoPage() {
        // Renderiza templates/web/video.html
        return "web/video";
    }
}

