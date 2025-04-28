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

}
