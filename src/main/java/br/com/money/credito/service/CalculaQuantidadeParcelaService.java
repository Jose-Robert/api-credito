package br.com.money.credito.service;

import br.com.money.credito.model.CreditoResponseTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculaQuantidadeParcelaService {

    public Integer calcularQuantidadeParcelas(CreditoResponseTO creditoResponseTO) {
        BigDecimal valorEmprestado = creditoResponseTO.getValorEmprestado();
        BigDecimal valorParcelas = creditoResponseTO.getValorParcela();
        Integer novoValorEmprestado = valorEmprestado.toBigInteger().intValue();
        Integer novoValorParcelas = valorParcelas.toBigInteger().intValue();
        return novoValorEmprestado / novoValorParcelas;
    }
}
