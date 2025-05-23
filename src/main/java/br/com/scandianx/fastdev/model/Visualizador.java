package br.com.scandianx.fastdev.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tvisualizadores")
public class Visualizador extends Usuario{

    @Column(nullable = false)
    private String nomeCompleto;

    @Override
    public boolean podeVisualizar(NivelAcesso nivelAcesso) {
        if (getRole() == UsuarioRole.ADMIN) return true;

        return switch (nivelAcesso) {
            case BRDEV -> getRole() == UsuarioRole.BRDEV || getRole() == UsuarioRole.GRINGADEV;
            case GRINGADEV -> getRole() == UsuarioRole.GRINGADEV;
            case FREE -> true;
            case PRIVATE -> false;
        };
    }

}
