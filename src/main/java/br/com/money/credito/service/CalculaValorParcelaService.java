package br.com.money.credito.service;

import br.com.money.credito.model.CreditoResponseTO;
import br.com.money.credito.utils.FaixaSalarial;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.money.credito.utils.Constantes.*;

@Service
public class CalculaValorParcelaService {

    public void calcularValorParcela(CreditoResponseTO creditoResponseTO) {
        BigDecimal salario = creditoResponseTO.getSalario();
        BigDecimal valorEmprestado = creditoResponseTO.getValorEmprestado();

        if (FaixaSalarial.isEntre1000And2000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(CINCO_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre2001And3000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(DEZ_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre3001And4000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(QUINZE_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre4001And5000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(VINTE_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre5001And6000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(VINTE_CINCO_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre6001And7000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(TRINTA_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre7001And8000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(TRINTA_CINCO_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isEntre8001And9000(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(QUARENTA_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
            return;
        }

        if (FaixaSalarial.isMaiorIgual9001(salario)) {
            BigDecimal valorParcela = valorEmprestado.multiply(QUARENTA_CINCO_PORCENTO);
            valorParcela = valorParcela.setScale(2, RoundingMode.HALF_UP);
            creditoResponseTO.setValorParcela(valorParcela);
        }
    }
}
