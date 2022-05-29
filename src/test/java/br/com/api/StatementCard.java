package br.com.api;

import br.com.utils.Paths;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.logging.Logger;

/**
 * @author Ricardo Burg Machado
 * Classe responsável por organizar as chamadas dos requests (RequestsCard) e suas respectivas validações de regras de negócio
 */
public class StatementCard extends RequestsCard {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    /**
     * Método responsável por criar um novo card
     * @param type
     * @return Response
     */
    public static Response create(String type){
        Response response=null;
        switch (type.toUpperCase()) {
            case "SUCESSO":
                response =  createCard(Paths.REQUEST_CREATE_CARD_SUCESSO, Paths.RESPONSE_CREATE_CARD_SUCESSO);
                break;
            case "FALHA_BODY_VAZIO":
                response =  createCard(Paths.REQUEST_CREATE_CARD_FALHA_BODY_VAZIO);
                break;

            case "FALHA_ID_LIST":
                response =  createCard(Paths.REQUEST_CREATE_CARD_ID_LIST_INVALID);
                break;
            case "FALHA_PERMISSAO_ACESSO":
                response =  createCardNotPermission(Paths.REQUEST_CREATE_CARD_FALHA_PERMISSAO_ACESSO);
                break;
            default:
                break;
        }
        return response;
    }

    /**
     * Método responsável por atualizar um card existente
     * @param type
     * @param responseCreate
     * @return Response
     */
    public static Response update(String type, Response responseCreate){
        Response response=null;
        switch (type.toUpperCase()) {
            case "SUCESSO":
                response =  updateCard(StatementCard.getIdCard(responseCreate), Paths.REQUEST_UPDATE_CARD_SUCESSO, Paths.RESPONSE_UPDATE_CARD_SUCESSO);
                break;
            case "BODY_VAZIO":
                response =  updateCard(StatementCard.getIdCard(responseCreate), Paths.REQUEST_UPDATE_CARD_BODY_VAZIO, Paths.RESPONSE_UPDATE_CARD_SUCESSO);
                break;
            case "FALHA_PERMISSAO_ACESSO":
                response =  updateCardNotPermission(StatementCard.getIdCard(responseCreate), Paths.REQUEST_UPDATE_CARD_FALHA_PERMISSAO_ACESSO);
                break;
            default:
                break;
        }
        return response;
    }

    /**
     * Método responsável por remover um card existente
     * @param type
     * @param responseCreate
     * @return
     */
    public static Response delete(String type, Response responseCreate){
        Response response=null;
        switch (type.toUpperCase()) {
            case "SUCESSO":
                response = deleteCard(StatementCard.getIdCard(responseCreate), Paths.RESPONSE_DELETE_CARD_SUCESSO);
                break;
            case "NULL":
                response = deleteCardNull();
                break;
            case "ID_INVALIDO":
                response = deleteCardIdInvalid();
                break;
            case "FALHA_PERMISSAO_ACESSO":
                response = deleteCardNotPermission(StatementCard.getIdCard(responseCreate));
                break;
            default:
                break;
        }
        return response;
    }

    /**
     * Método reponsável por obter um card existente através de seu ID
     * @param response
     * @return String
     */
    public static String getIdCard(Response response){
        if(response.getStatusCode() == 200 && response.getBody().asString().contains("\"id\"")){
            JsonPath res = new JsonPath(response.getBody().asString());
            return res.get("id");
        }else{
            return "";
        }
    }

    /**
     * Método responsável por verificar/validar a existência de um card
     * @param response
     * @return boolean
     */
    public static boolean verifyExistsCard(Response response){
        if(!getIdCard(response).isEmpty()){
            if(RequestsCard.getCard(getIdCard(response)).getStatusCode() == 200){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Método responsável por realizar a remoção dinâmica de um card, ou seja, caso ele ainda exista.
     * @param response
     */
    public  void clearCard(Response response){
        if(StatementCard.verifyExistsCard(response)){
            RequestsCard.deleteCard(StatementCard.getIdCard(response));
            LOGGER.info("\n Deletou o card com sucesso | id: "+ StatementCard.getIdCard(response));
        }else{
            LOGGER.info("\n Nao Deletou o card | id: "+ StatementCard.getIdCard(response));
        }
    }

    /**
     * Método responsável por realizar a remoção dinâmica de um card, ou seja, caso ele ainda exista.
     * @param idCard
     */
    public  void clearCard(String idCard){
        if(!idCard.isEmpty()){
            if(RequestsCard.getCard(idCard).getStatusCode() == 200){
                if(RequestsCard.deleteCard(idCard).getStatusCode() == 200){
                    LOGGER.info("\n Deletou o card com sucesso | id: "+ idCard);
                }else{
                    LOGGER.info("\n Nao foi possivel deletar o card | id: "+ idCard);
                }
            }else{
                LOGGER.info("\n Nao Deletou o card ->  id: "+ idCard);
            }
        }else{
            LOGGER.info("\n Nao Deletou o card | id: "+ idCard);
        }
    }

    /**
     * Método responsável por validar a criação de um card com sucesso
     * @param response
     */
    public static void validCreateCardSuccess(Response response){
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals("card name", jsonPath.get("name"));
        Assert.assertEquals("your description", jsonPath.get("desc"));
        Assert.assertEquals("62901a8dddd4bf5a7816330a", jsonPath.getString("idBoard"));
    }

    /**
     * Método responsável por validar a tentantiva de criação de um card com falha (invalid value for idList)
     * @param response
     */
    public static void validCreateCardBodyEmptyFail(Response response){
        Assert.assertEquals(400,response.getStatusCode());
        Assert.assertEquals("invalid value for idList",response.getBody().prettyPrint());
    }

    /**
     * Método responsável por validar a tentantiva de criação de um card com falha (invalid value for idList)
     * @param response
     */
    public static void validCreateCardBodyIdListInvalid(Response response){
        Assert.assertEquals(400,response.getStatusCode());
        Assert.assertEquals("invalid value for idList",response.getBody().prettyPrint());
    }

    /**
     * Método responsável por validar a atualização de um card com sucesso (mesmo com o body empty)
     * @param response
     */
    public static void validUpdateCardBodyEmptySuccess(Response response){
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals("card name", jsonPath.get("name"));
        Assert.assertEquals("your description", jsonPath.get("desc"));
        Assert.assertEquals("62901a8dddd4bf5a7816330a", jsonPath.getString("idBoard"));
    }

    /**
     * Método responsável por validar a tentantiva de criação de um card com falha (unauthorized card permission requested)
     * @param response
     */
    public static void validCreateCardNotPermissionFail(Response response){
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("unauthorized card permission requested", response.getBody().prettyPrint());
    }

    /**
     * Método responsável por validar a tentantiva de atualização de um card com falha (unauthorized card permission requested)
     * @param response
     */
    public static void validUpdateCardNotPermissionFail(Response response){
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("unauthorized card permission requested", response.getBody().prettyPrint());
    }

    /**
     * Método responsável por validar a atualização de um card com sucesso
     * @param response
     */
    public static void validUpdateCardSuccess(Response response){
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals("nome do card atualizado", jsonPath.get("name"));
        Assert.assertEquals("descricao card atualizado", jsonPath.get("desc"));
        Assert.assertEquals("62901a8dddd4bf5a7816330a", jsonPath.getString("idBoard"));
    }

    /**
     * Método responsável por validar a remoção de um card com sucesso
     * @param response
     */
    public static void validDeleteCardSuccess(Response response){
        Assert.assertEquals(response.getStatusCode(),200);
    }

    /**
     * Método responsável por validar a remoção de um card com falha (card id nulo)
     * @param response
     */
    public static void validDeleteCardNullFail(Response response){
        Assert.assertEquals(404, response.getStatusCode());
    }

    /**
     * Método responsável por validar a tentantiva de remoção de um card com falha (unauthorized card permission requested)
     * @param response
     */
    public static void validRemoveCardNotPermissionFail(Response response){
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("unauthorized card permission requested", response.getBody().prettyPrint());
    }

    /**
     * Método responsável por validar a tentantiva de remoção de um card com falha (invalid id)
     * @param response
     */
    public static void validDeleteCardBodyIdInvalid(Response response){
        Assert.assertEquals(400,response.getStatusCode());
        Assert.assertEquals("invalid id",response.getBody().prettyPrint());
    }


}
