package br.com.money.credito.controller;

import br.com.money.credito.model.CreditoResponseTO;
import br.com.money.credito.service.CreditoService;
import br.com.money.credito.service.RestConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/credito")
public class CreditoController {

    @Autowired
    private RestConsumerService restConsumerService;

    @Autowired
    private CreditoService creditoService;

    @GetMapping
    public ResponseEntity<String> findAll() {
        String response = restConsumerService.getListJsonObjects();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{nome}/{valorPedido}")
    public ResponseEntity<CreditoResponseTO> verificarDisponibilidadeCredito(@PathVariable String nome, @PathVariable String valorPedido) {
        CreditoResponseTO responseTO = creditoService.consultarDisponibilidade(nome, new BigDecimal(valorPedido));
        return ResponseEntity.ok().body(responseTO);
    }

}
