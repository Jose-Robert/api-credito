package br.com.money.credito.service;

import br.com.money.credito.model.CreditoResponseTO;
import br.com.money.credito.model.Response;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static br.com.money.credito.utils.Constantes.*;

@Service
public class CalculaCreditoPorIdadeService {

    public void calcularCreditoPorIdade(Response response, CreditoResponseTO creditoResponseTO) {
        Integer idade = response.getIdade();

        if (idade >= IDADE_VINTE && idade < IDADE_TRINTA) {
            BigDecimal valorEmprestado = response.getSalario();
            creditoResponseTO.setValorEmprestado(valorEmprestado);
            return;
        }

        if (idade >= IDADE_TRINTA && idade < IDADE_CINQUENTA) {
            BigDecimal valorEmprestado = response.getSalario().multiply(NOVENTA_PORCENTO);
            creditoResponseTO.setValorEmprestado(valorEmprestado);
            return;
        }

        if (idade >= IDADE_CINQUENTA && idade < IDADE_OITENTA) {
            BigDecimal valorEmprestado = response.getSalario().multiply(SETENTA_PORCENTO);
            creditoResponseTO.setValorEmprestado(valorEmprestado);
            return;
        }

        if (idade >= IDADE_OITENTA) {
            BigDecimal valorEmprestado = response.getSalario().multiply(VINTE_PORCENTO);
            creditoResponseTO.setValorEmprestado(valorEmprestado);
        }
    }
}
