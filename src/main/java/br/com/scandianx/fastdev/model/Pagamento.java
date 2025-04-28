package br.com.scandianx.fastdev.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tpagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_plano", nullable = false)
    private TipoPlano tipoPlano;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_final", nullable = false)
    private LocalDateTime dataFinal;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Pagamento(Usuario usuario, BigDecimal valor, TipoPagamento tipoPagamento,
                     TipoPlano tipoPlano, LocalDateTime dataInicio, LocalDateTime dataFinal) {
        this.usuario = usuario;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.tipoPlano = tipoPlano;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }
}
