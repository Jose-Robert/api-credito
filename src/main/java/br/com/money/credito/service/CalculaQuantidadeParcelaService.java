package br.com.money.credito.service;

import br.com.money.credito.model.CreditoResponseTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculaQuantidadeParcelaService {

    public Integer calcularQuantidadeParcelas(CreditoResponseTO creditoResponseTO) {
        BigDecimal valorEmprestado = creditoResponseTO.getValorEmprestado();
        BigDecimal valorParcelas = creditoResponseTO.getValorParcela();
        MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN) ;
        return valorEmprestado.divide(valorParcelas, mathContext).precision();
    }
}
