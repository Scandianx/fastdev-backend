package br.com.scandianx.fastdev.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tvideo_base")
public abstract class VideoAbstrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descricao;

    private LocalDateTime dtCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAcesso nivelAcesso;

    public Long getId() {
        return id;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDtCriacao() {
        return dtCriacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public abstract String getUrl();
}
