package br.com.money.credito.controller;


import br.com.money.credito.service.CreditoService;
import br.com.money.credito.service.RestConsumerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
class CreditoControllerTests {

    public static final String BASE_URL = "/credito";

    @Autowired
    private RestConsumerService service;

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = Mockito.spy(service);
        creditoService = Mockito.spy(creditoService);
    }

    @Test
    @DisplayName("Deve retornar status code 200, para a requisição.")
    void deveRetornarSucessoIsOk_200() throws Exception {

        when(service.getListJsonObjects()).thenReturn("");

        MvcResult mvcResult = mockMvc.perform(
                get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Deve retornar status code 200, no momento da consulta de disponibilidade")
    void deveRetornarSucessoIsOk_200_NaConsultaDisponibilidadeCreditoEmprestimo() throws Exception {

        String nome = "Andrea Schwarzenegger";
        String valorPedido = "5000";

        MvcResult mvcResult = mockMvc.perform(
                get(BASE_URL + "/" + nome + "/" + valorPedido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("Deve retornar status code 400 BAD REQUEST - CLIENT ERROR")
    void deveRetornarBadRequest_400_NaConsultaDisponibilidadeCreditoEmprestimo() throws Exception {

        String nome = "Andrea Schwarzenegger";
        String valorPedido = "0";

        MvcResult mvcResult = mockMvc.perform(
                get(BASE_URL + "/" + nome + "/" + valorPedido)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(400);
    }
}
