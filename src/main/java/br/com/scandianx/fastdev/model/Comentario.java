package br.com.scandianx.fastdev.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tcomentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private LocalDateTime criadoEm = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private VideoAbstrato video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Comentario() {
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public VideoAbstrato getVideo() {
        return video;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setTexto(String t) {
        this.texto = t;
    }

    public void setCriadoEm(LocalDateTime dt) {
        this.criadoEm = dt;
    }

    public void setVideo(VideoAbstrato v) {
        this.video = v;
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;
    }
}
