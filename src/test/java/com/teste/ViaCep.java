package com.teste;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class ViaCep {

    @BeforeClass
    public static void urlbase() {
        RestAssured.baseURI = "https://viacep.com.br/ws/";
    }

    @Test
    public void dadoViaCepComCep94440360_QuandoBuscarDados_EntaoDeveTerDDD51() {
        given().contentType("application/json")
                .pathParam("cep", "94440360")
                .when()
                .get("{cep}/json")
                .then()
                .assertThat()
                .body("ddd", equalTo("51"))
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    public void dadoViaCepComCep94440361_QuandoBuscarDados_EntaoDeveRetornarErrTrue() {
        given().contentType("application/json")
                .pathParam("cep", "94440361")
                .when()
                .get("{cep}/json")
                .then()
                .assertThat()
                .body("erro", equalTo("true"))
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    public void dadoViaCepComCep9444036o_QuandoBuscarDados_EntaoDeveRetornarStatusCode400() {
        given().contentType("application/json")
                .pathParam("cep", "9444036o")
                .when()
                .get("{cep}/json")
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    public void dadoViaCepComEstadoRSCidadeViamaoRuaAlvarez_QuandoBuscarDados_EntaoDeveTerDDD51() {
        given().contentType("application/json")
                .pathParam("estado", "rs")
                .pathParam("cidade", "viamão")
                .pathParam("rua", "alvarez")
                .when()
                .get("{estado}/{cidade}/{rua}/json")
                .then()
                .assertThat()
                .body("ddd[0]", equalTo("51"))
                .statusCode(200)
                .contentType("application/json");
    }
}
