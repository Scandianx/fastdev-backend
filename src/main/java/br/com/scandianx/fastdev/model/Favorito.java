package br.com.scandianx.fastdev.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tvideos_favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private VideoAbstrato video;

    private LocalDateTime favoritadoEm = LocalDateTime.now();

    public Favorito() {
    }

    public Favorito(Usuario u, VideoAbstrato v) {
        this.usuario = u;
        this.video = v;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public VideoAbstrato getVideo() {
        return video;
    }

    public LocalDateTime getFavoritadoEm() {
        return favoritadoEm;
    }

    public void setUsuario(Usuario u) {
        this.usuario = u;
    }

    public void setVideo(VideoAbstrato v) {
        this.video = v;
    }

    public void setFavoritadoEm(LocalDateTime dt) {
        this.favoritadoEm = dt;
    }
}
