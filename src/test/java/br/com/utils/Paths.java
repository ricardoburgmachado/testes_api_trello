package br.com.utils;

/**
 * Classe responsável por organizar os caminhos dos arquivos de requests (bodys) e reponses (schemas de validação)
 * @author Ricardo Burg Machado
 */
public enum Paths {

    REQUEST_CREATE_CARD_SUCESSO("src/test/resources/requests/REQUEST_CREATE_CARD_SUCESSO.json"),
    REQUEST_UPDATE_CARD_SUCESSO("src/test/resources/requests/REQUEST_UPDATE_CARD_SUCESSO.json"),
    REQUEST_CREATE_CARD_FALHA_BODY_VAZIO("src/test/resources/requests/REQUEST_CREATE_CARD_FALHA_BODY_VAZIO.json"),
    REQUEST_UPDATE_CARD_FALHA_PERMISSAO_ACESSO("src/test/resources/requests/REQUEST_UPDATE_CARD_FALHA_PERMISSAO_ACESSO.json"),
    REQUEST_UPDATE_CARD_BODY_VAZIO("src/test/resources/requests/REQUEST_UPDATE_CARD_BODY_VAZIO.json"),
    REQUEST_CREATE_CARD_FALHA_PERMISSAO_ACESSO("src/test/resources/requests/REQUEST_CREATE_CARD_FALHA_PERMISSAO_ACESSO.json"),
    REQUEST_CREATE_CARD_ID_LIST_INVALID("src/test/resources/requests/REQUEST_CREATE_CARD_ID_LIST_INVALID.json"),

    RESPONSE_CREATE_CARD_SUCESSO("responses/RESPONSE_CREATE_CARD_SUCESSO.json"),
    RESPONSE_UPDATE_CARD_SUCESSO("responses/RESPONSE_UPDATE_CARD_SUCESSO.json"),
    RESPONSE_DELETE_CARD_SUCESSO("responses/RESPONSE_DELETE_CARD_SUCESSO.json")
    ;


    private String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
