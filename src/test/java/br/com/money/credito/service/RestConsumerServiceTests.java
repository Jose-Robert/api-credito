package br.com.money.credito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class RestConsumerServiceTests {

    private static final String URL_MOCKY_IO = "http://www.mocky.io/v2/5e5ec624310000b738afd85a";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestConsumerService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        restTemplate = Mockito.spy(restTemplate);
        service = Mockito.spy(service);
    }

    @Test
    @DisplayName("Deve retornar status code 200 na consulta a API")
    void deveConsultarApiExternaComSucesso() {
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<String> response = restTemplate.exchange(URL_MOCKY_IO, HttpMethod.GET, null, responseType);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    @DisplayName("Deve consultar API para trazer a lista de pessoas com os dados.")
    void deveRetornarListaDeObjetos() {
        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<String> response = restTemplate.exchange(URL_MOCKY_IO, HttpMethod.GET, null, responseType);
        assertThat(service.getListJsonObjects()).isEqualTo(response.getBody());
    }

}