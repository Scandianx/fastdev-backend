package br.com.scandianx.fastdev.dto;



import java.time.LocalDateTime;

public class VideoDTO {

    private Long id;
    private String titulo;
    private String url;
    private String descricao;
    private LocalDateTime dtCriacao;
    private String nivelAcesso;

    public VideoDTO() {}

    public VideoDTO(Long id, String titulo, String url, String descricao, LocalDateTime dtCriacao, String nivelAcesso) {
        this.id = id;
        this.titulo = titulo;
        this.url = url;
        this.descricao = descricao;
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
    public String getDescricao() { return descricao; }

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
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}

