package br.com.scandianx.fastdev.dto;



import java.time.LocalDateTime;

public class VideoDTO {

    private Long id;
    private String titulo;
    private String url;
    private LocalDateTime dtCriacao;
    private String nivelAcesso;

    public VideoDTO() {}

    public VideoDTO(Long id, String titulo, String url, LocalDateTime dtCriacao, String nivelAcesso) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.dtCriacao = dtCriacao;
        this.nivelAcesso = nivelAcesso;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}

