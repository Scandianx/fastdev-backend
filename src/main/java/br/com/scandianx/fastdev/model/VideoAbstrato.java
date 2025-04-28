package br.com.scandianx.fastdev.model;


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

    private LocalDateTime dtCriacao = LocalDateTime.now();

    
    public Long getId() {
        return id;
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

    public void setDtCriacao(LocalDateTime dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    
    
    public abstract String getUrl();
}
