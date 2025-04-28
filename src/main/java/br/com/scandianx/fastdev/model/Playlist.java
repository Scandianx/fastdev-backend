package br.com.scandianx.fastdev.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tplaylists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private LocalDateTime dtCriacao = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "playlist_video", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "video_id"))
    private List<VideoAbstrato> videos = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public List<VideoAbstrato> getVideos() {
        return videos;
    }

    public void setTitulo(String t) {
        this.titulo = t;
    }

    public void setDtCriacao(LocalDateTime dt) {
        this.dtCriacao = dt;
    }

    public void setVideos(List<VideoAbstrato> v) {
        this.videos = v;
    }
}
