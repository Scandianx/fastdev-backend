package br.com.scandianx.fastdev.entity;
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
            case BRLDEV -> getRole() == UsuarioRole.BRLDEV || getRole() == UsuarioRole.USDEV;
            case USDEV -> getRole() == UsuarioRole.USDEV;
            case FREE -> true;
            case PRIVATE -> false;
        };
    }

}
