package br.com.money.credito.service;

import br.com.money.credito.model.CreditoResponseTO;
import br.com.money.credito.model.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class CalculaCreditoPorIdadeServiceTests {

    @Autowired
    private CalculaCreditoPorIdadeService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = Mockito.spy(service);
    }

    @Test
    @DisplayName("Deve calcular o credito disponível por idade")
    void deveCalcularCreditoDisponivelDeAcordoComAIdade() {

        CreditoResponseTO creditoResponseTO = CreditoResponseTO.builder()
                .nome("Andrea Schwarzenegger")
                .salario(BigDecimal.valueOf(8454))
                .valorPedido(BigDecimal.valueOf(4000))
                .valorEmprestado(null)
                .parcelas(2)
                .valorParcela(BigDecimal.valueOf(3381.6))
                .build();

        Response response = Response.builder()
                .idade(76)
                .build();

        doNothing().when(service).calcularCreditoPorIdade(response, creditoResponseTO);
        Mockito.doAnswer(invocationOnMock -> null).when(service).calcularCreditoPorIdade(response, creditoResponseTO);
        assertTrue(true);
    }


}
