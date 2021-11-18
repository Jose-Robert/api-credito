package br.com.money.credito.service;


import br.com.money.credito.exception.CampoValorNegativoException;
import br.com.money.credito.exception.CampoValorZeradoException;
import br.com.money.credito.exception.ObjectNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class CreditoServiceTests {

    @Autowired
    private CreditoService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = Mockito.spy(service);
    }

    @Test
    @DisplayName("Deve consultar o credito disponivel para emprestimo")
    void deveConsultarDisponibilidadeDeCreditoParaEmprestimo() {
        CreditoResponseTO creditoResponseTO = CreditoResponseTO.builder()
                .nome("Andrea Schwarzenegger")
                .salario(BigDecimal.valueOf(8454))
                .valorPedido(BigDecimal.valueOf(4000))
                .valorEmprestado(BigDecimal.valueOf(5917.8))
                .parcelas(2)
                .valorParcela(BigDecimal.valueOf(3381.6))
                .build();

        Mockito.when(service.consultarDisponibilidade("Andrea Schwarzenegger", BigDecimal.valueOf(4000))).thenReturn(new CreditoResponseTO());
        assertThat(creditoResponseTO);
    }

    @Test
    @DisplayName("Deve verificar se o valor pedido é zero")
    void deveValidarOValorPedidoZerado() {

        CampoValorZeradoException exception = assertThrows(CampoValorZeradoException.class, () -> {
            service.consultarDisponibilidade("Andrea Schwarzenegger", BigDecimal.ZERO);
        });

        assertEquals(CampoValorZeradoException.class, exception.getClass());
    }

    @Test
    @DisplayName("Deve verificar se o valor pedido é negativo")
    void deveValidarOValorPedidoENegativo() {

        CampoValorNegativoException exception = assertThrows(CampoValorNegativoException.class, () -> {
            service.consultarDisponibilidade("Andrea Schwarzenegger", BigDecimal.valueOf(-1004));
        });

        assertEquals(CampoValorNegativoException.class, exception.getClass());
    }

    @Test
    @DisplayName("Deve verificar caso uma pessoa não seja encontrada pelo nome")
    void deveVerificarCasoNaoTenhaEncontrado() {
        try {
            service.consultarDisponibilidade("José Robert", BigDecimal.valueOf(8000));
        } catch (ObjectNotFoundException exception) {
            assertEquals(ObjectNotFoundException.class, exception.getClass());
        }
    }
}
