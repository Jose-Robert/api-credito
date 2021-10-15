package br.com.money.credito.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {

    @SerializedName("Nome")
    private String nome;
    @SerializedName("Idade")
    private Integer idade;
    @SerializedName("Salario")
    private BigDecimal salario;
}
