package br.com.scandianx.fastdev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tvideo_youtube")
public class VideoYoutube extends VideoAbstrato {

    private String urlYouTube;

    @Override
    public String getUrl() {
        return urlYouTube;
    }

    public void setUrlYouTube(String urlYouTube) {
        this.urlYouTube = urlYouTube;
    }

    public VideoYoutube(String urlYouTube) {
        this.urlYouTube = urlYouTube;
    }

    public VideoYoutube() {
    }
    
}
