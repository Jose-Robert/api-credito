package br.com.money.credito.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreditoControllerRestAssuredTests {

    public static final String BASE_URI = "/api/v1/credito/";

    @Test
    @DisplayName("Quando a api retorna status 200 sucesso, na consulta a lista de objetos JSON.")
    void quandoApiRetornaStatusSucesso_200_GetListObjects() {
        given()
                .when()
                .get(BASE_URI)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    @DisplayName("Quando a api retorna status 200 sucesso, na consulta da disponibilidade.")
    void quandoApiRetornaStatusSucesso_200_GetConsultarDisponibilidadeCredito() {

        String nome = "Andrea Schwarzenegger";
        String valorPedido = "5000";

        Response response = given()
                .log()
                .all()
                .when()
                .get(BASE_URI + nome + "/" +valorPedido);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    @DisplayName("Quando a api retorna 400 BAD REQUEST, dado o valor do pedido zero.")
    void quandoApiRetornaStatusBadRequest_400_GetConsultarDisponibilidadeCredito() {

        String nome = "Andrea Schwarzenegger";
        String valorPedido = "0";

        given()
                .when()
                .get(BASE_URI + nome + "/" + valorPedido)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .log()
                .all();

    }

    @Test
    @DisplayName("Quando a api retorna 400 BAD REQUEST, dado o valor do pedido negativo.")
    void quandoApiRetornaStatusBadRequest_400_GetConsultarDisponibilidadeCreditoValorPedidoNegativo() {

        String nome = "Andrea Schwarzenegger";
        String valorPedido = "-2124";

        given()
                .when()
                .get(BASE_URI + nome + "/" + valorPedido)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .log()
                .all();
    }

}
