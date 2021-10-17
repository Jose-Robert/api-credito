package br.com.money.credito.service;

import br.com.money.credito.model.CreditoResponseTO;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class CalculaQuantidadeParcelaServiceTests {

    @Autowired
    private CalculaQuantidadeParcelaService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = Mockito.spy(service);
    }

    @Test
    @DisplayName("Deve realizar o calculo de parcelas de acordo com o comprometimento parcial do salário.")
    void deveCalcularAQuantidadeDeParcelas() {
        CreditoResponseTO creditoResponseTO = CreditoResponseTO.builder()
                .nome("Andrea Schwarzenegger")
                .salario(BigDecimal.valueOf(8454))
                .valorPedido(BigDecimal.valueOf(4000))
                .valorEmprestado(BigDecimal.valueOf(5917.8))
                .parcelas(null)
                .valorParcela(BigDecimal.valueOf(3381.6))
                .build();

        assertThat(service.calcularQuantidadeParcelas(creditoResponseTO));
        Mockito.verify(service, Mockito.times(1)).calcularQuantidadeParcelas(creditoResponseTO);
    }
}
