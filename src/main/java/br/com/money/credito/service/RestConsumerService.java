package br.com.money.credito.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.mocky.io}")
    private String url;

    public String getListJsonObjects() {
        log.info("Consultando API externa...");
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody();
    }
}
