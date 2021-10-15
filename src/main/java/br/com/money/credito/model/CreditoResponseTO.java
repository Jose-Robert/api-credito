package br.com.money.credito.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditoResponseTO implements Serializable {

    private String nome;
    private BigDecimal salario;
    private BigDecimal valorPedido;
    private BigDecimal valorEmprestado;
    private Integer parcelas;
    private BigDecimal valorParcela;
}
