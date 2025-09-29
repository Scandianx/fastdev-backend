package br.com.scandianx.fastdev.components;

import br.com.scandianx.fastdev.dto.VideoRequestDTO;
import br.com.scandianx.fastdev.entity.NivelAcesso;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class VideoInitializer {

    private final VideoFactory videoFactory;

    public VideoInitializer(VideoFactory videoFactory) {
        this.videoFactory = videoFactory;
    }

    @PostConstruct
    public void criarVideosSeNaoExistirem() {
        // Vídeo 1
        VideoRequestDTO v1 = new VideoRequestDTO();
        v1.setTipo("YOUTUBE");
        v1.setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        v1.setTitulo("Introdução ao FastDev");
        v1.setDescricao("Primeiros passos com a plataforma FastDev");
        v1.setNivelAcesso(NivelAcesso.FREE);
        videoFactory.createAndSaveVideo(v1);

        // Vídeo 2
        VideoRequestDTO v2 = new VideoRequestDTO();
        v2.setTipo("YOUTUBE");
        v2.setUrl("https://www.youtube.com/watch?v=abc123xyz");
        v2.setTitulo("Funcionalidades Avançadas");
        v2.setDescricao("Demonstração de recursos exclusivos");
        v2.setNivelAcesso(NivelAcesso.FREE);
        videoFactory.createAndSaveVideo(v2);

        // Vídeo 3
        VideoRequestDTO v3 = new VideoRequestDTO();
        v3.setTipo("YOUTUBE");
        v3.setUrl("https://www.youtube.com/watch?v=987zyx321");
        v3.setTitulo("Painel Administrativo");
        v3.setDescricao("Configurações internas do sistema");
        v3.setNivelAcesso(NivelAcesso.FREE);
        videoFactory.createAndSaveVideo(v3);
    }
}
