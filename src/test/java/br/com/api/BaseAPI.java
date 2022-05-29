package br.com.api;

import br.com.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig;

/**
 * Classe reponsável por organizar e definir configurações da API
 * @author Ricardo Burg Machado
 */
public abstract class BaseAPI {

    protected final static String baseUri = Utils.getTestProperty("base_uri");
    private final static String basePath = Utils.getTestProperty("base_path");
    protected static String  consumerKey = Utils.getTestProperty("consumer_key");
    protected static String  accessToken = Utils.getTestProperty("access_token");

    public static void init() {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        RestAssured.config = RestAssuredConfig.newConfig()
                .jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));

    }

}
