package br.com.money.credito.service;

import br.com.money.credito.exception.CampoValorNegativoException;
import br.com.money.credito.exception.CampoValorZeradoException;
import br.com.money.credito.model.CreditoResponseTO;
import br.com.money.credito.model.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class CreditoService {

    @Autowired
    private RestConsumerService restConsumerService;

    @Autowired
    private CalculaValorParcelaService valorParcelaService;

    @Autowired
    private CalculaCreditoPorIdadeService creditoPorIdadeService;

    @Autowired
    private CalculaQuantidadeParcelaService parcelaService;


    public CreditoResponseTO consultarDisponibilidade(String nome, BigDecimal valorPedido) {

        validarValorDeEntrada(valorPedido);
        List<Response> responseList = this.getResponseList();

        CreditoResponseTO creditoResponseTO = new CreditoResponseTO();
        for (Response response : responseList) {
            if (response.getNome().equalsIgnoreCase(nome)) {
                creditoResponseTO.setNome(response.getNome());
                creditoResponseTO.setSalario(response.getSalario());
                creditoResponseTO.setValorPedido(valorPedido);
                calcularValorDisponivelEmprestimo(response, creditoResponseTO);
                valorParcelaService.calcularValorParcela(creditoResponseTO);
                creditoResponseTO.setParcelas(parcelaService.calcularQuantidadeParcelas(creditoResponseTO));
                break;
            }
        }
        return creditoResponseTO;
    }

    private List<Response> getResponseList() {
        String jsonObjects = restConsumerService.getListJsonObjects();
        Gson gson = new Gson();
        return gson.fromJson(jsonObjects, new TypeToken<List<Response>>(){}.getType());
    }

    private void calcularValorDisponivelEmprestimo(Response response, CreditoResponseTO creditoResponseTO) {
        this.creditoPorIdadeService.calcularCreditoPorIdade(response, creditoResponseTO);
    }

    private void validarValorDeEntrada(BigDecimal valorPedido) {
        if (valorPedido.compareTo(BigDecimal.ZERO) == 0) {
            throw new CampoValorZeradoException("Valor Pedido");
        }

        if (valorPedido.compareTo(BigDecimal.valueOf(-1)) <= 0) {
            throw new CampoValorNegativoException("Valor Pedido");
        }
    }

}
