package br.com.money.credito.service;

import br.com.money.credito.exception.CampoValorNegativoException;
import br.com.money.credito.exception.CampoValorZeradoException;
import br.com.money.credito.exception.ObjectNotFoundException;
import br.com.money.credito.model.CreditoResponseTO;
import br.com.money.credito.model.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        Optional<Response> responseOptional = getResponseList().stream()
                .filter(response -> response.getNome().equalsIgnoreCase(nome)).findFirst();

        if (responseOptional.isEmpty()) {
           throw new ObjectNotFoundException();
        }

        CreditoResponseTO creditoResponseTO = new CreditoResponseTO();
        creditoResponseTO.setNome(responseOptional.get().getNome());
        creditoResponseTO.setSalario(responseOptional.get().getSalario());
        creditoResponseTO.setValorPedido(valorPedido);
        calcularValorDisponivelEmprestimo(responseOptional.get(), creditoResponseTO);
        valorParcelaService.calcularValorParcela(creditoResponseTO);
        creditoResponseTO.setParcelas(parcelaService.calcularQuantidadeParcelas(creditoResponseTO));

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
            throw new CampoValorZeradoException();
        }

        if (valorPedido.compareTo(BigDecimal.valueOf(-1)) <= 0) {
            throw new CampoValorNegativoException();
        }
    }

}
