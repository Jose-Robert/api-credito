package br.com.money.credito.service;


import br.com.money.credito.exception.CampoValorZeradoException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class MessageServiceTests {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CreditoService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        messageService = Mockito.spy(messageService);
        service = Mockito.spy(service);
    }

    @Test
    @DisplayName("Deve retornar qualquer tipo de mensagem")
    void deveRetornarUmaMensagem() {

        CampoValorZeradoException exception = assertThrows(CampoValorZeradoException.class, () -> {
            service.consultarDisponibilidade("Andrea Schwarzenegger", BigDecimal.ZERO);
        });

        String key = "validacao.campo-valor-zerado";
        Object[] args = {exception.getMessage()};

        assertThat(messageService.getMessage(key, args));
        Mockito.verify(messageService, Mockito.times(1)).getMessage(key, args);
    }
}
