package br.com.api;


import br.com.utils.Paths;
import br.com.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

/**
 * @author Ricardo Burg Machado
 * Classe reponsável por organizar os requests que serão manipulados pela classe StatementCard
 */
public class RequestsCard extends BaseAPI {

    public RequestsCard(){}

    /**
     * Método responsável por criar um novo card a partir de um board definido no body
     * É realizada a validação de contrato na resposta
     * @param pathRequest
     * @param pathResponse
     * @return Response
     */
    protected static Response createCard(Paths pathRequest, Paths pathResponse) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body(Utils.getBodyJSON(pathRequest))
                .when()
                .post("")
                .then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(pathResponse.getPath()))
                .extract().response();
        return response;
    }

    /**
     * Método responsável por criar um novo card a partir de um board definido no body
     * Não é realizada a validação de contrato na resposta
     * @param pathRequest
     * @return Response
     */
    protected static Response createCard(Paths pathRequest) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body(Utils.getBodyJSON(pathRequest))
                .when()
                .post("")
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por criar um novo card SEM o parâmetro TOKEN de acesso
     * @param pathRequest
     * @return Response
     */
    protected static Response createCardNotPermission(Paths pathRequest) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                //.queryParam("token", accessToken)
                .body(Utils.getBodyJSON(pathRequest))
                .when()
                .post("")
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por atualizar um card existente
     * É realizada a validação de contrato na resposta
     * @param idCard
     * @param pathRequest
     * @param pathResponse
     * @return Response
     */
    protected static Response updateCard(String idCard, Paths pathRequest, Paths pathResponse) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body(Utils.getBodyJSON(pathRequest))
                .when()
                .put("/"+idCard)
                .then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(pathResponse.getPath()))
                .extract().response();
        return response;
    }

    /**
     * Método responsável por atualizar um card existente
     * Não é realizada a validação de contrato na resposta
     * @param idCard
     * @param path
     * @return Response
     */
    protected static Response updateCard(String idCard, Paths path) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body(Utils.getBodyJSON(path))
                .when()
                .put("/"+idCard)
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por atualizar um card existente SEM o parâmetro TOKEN de acesso
     * @param idCard
     * @param path
     * @return Response
     */
    protected static Response updateCardNotPermission(String idCard, Paths path) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                //.queryParam("token", accessToken)
                .body(Utils.getBodyJSON(path))
                .when()
                .post("")
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por remover um card existente
     * @param idCard
     * @return Response
     */
    protected static Response deleteCard(String idCard) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body("{}")
                .when()
                .delete("/"+idCard)
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por remover um card existente
     * Realiza a validação de contrato do response
     * @param idCard
     * @param pathResponse
     * @return
     */
    protected static Response deleteCard(String idCard, Paths pathResponse) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body("{}")
                .when()
                .delete("/"+idCard)
                .then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(pathResponse.getPath()))
                .extract().response();
        return response;
    }

    /**
     * Método responsável por remover um card nulo
     * @return Response
     */
    protected static Response deleteCardNull() {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body("{}")
                .when()
                .delete("/")
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por remover um card com valor inválido
     * @return Response
     */
    protected static Response deleteCardIdInvalid() {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body("{}")
                .when()
                .delete("/0000")
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por remover um card existente sem o TOKEN de acesso
     * @param idCard
     * @return Response
     */
    protected static Response deleteCardNotPermission(String idCard) {

        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                //.queryParam("token", accessToken)
                .body("{}")
                .when()
                .delete("/"+idCard)
                .then()
                .extract().response();
        return response;
    }

    /**
     * Método responsável por obter um card existente a partir de seu ID
     * @param idCard
     * @return Response
     */
    protected static Response getCard(String idCard) {
        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Connection", "keep-alive")
                .header("Content-Type","application/json")
                .queryParam("key", consumerKey)
                .queryParam("token", accessToken)
                .body("{}")
                .when()
                .get("/"+idCard)
                .then()
                .extract().response();
        return response;
    }

}

