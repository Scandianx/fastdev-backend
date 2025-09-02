package br.com.scandianx.fastdev.dto;

import org.hibernate.validator.constraints.URL;
import br.com.scandianx.fastdev.entity.NivelAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class VideoRequestDTO {
    @NotBlank(message = "tipo obrigatório")
    @Pattern(regexp = "(?i)youtube", message = "tipo deve ser youtube")
    private String tipo;

    @NotBlank(message = "url obrigatória")
    @URL(message = "url inválida")
    private String url;

    @NotBlank(message = "título obrigatório")
    @Size(max = 120, message = "título deve ter no máximo 120 caracteres")
    private String titulo;

    @NotBlank(message = "descrição obrigatória")
    @Size(max = 1000, message = "descrição deve ter no máximo 1000 caracteres")
    private String descricao;

    @NotNull(message = "nível de acesso obrigatório")
    private NivelAcesso nivelAcesso;

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public NivelAcesso getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(NivelAcesso nivelAcesso) { this.nivelAcesso = nivelAcesso; }
}
