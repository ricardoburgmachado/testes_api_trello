package br.com.steps;

import br.com.api.StatementCard;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

public class CardSteps {

    private Response response;
    private String idCard="";

    @Dado("que acesso a api da Trello")
    public void queAcessoAApiDaTrello() {
        StatementCard.init();
    }

    @Quando("crio um novo card {string}")
    public void crioUmNovoCard(String type) {
        response = StatementCard.create(type);
        idCard = StatementCard.getIdCard(response);
    }

    @Entao("valido o card criado com sucesso")
    public void validoOCardCriadoComSucesso() {
        StatementCard.validCreateCardSuccess(response);
    }

    @Entao("valido o card criado com falha de permissao de acesso")
    public void validoOCardCriadoComFalhaDePermissaoDeAcesso() {
        StatementCard.validCreateCardNotPermissionFail(response);
    }

    @Entao("valido o card criado com falha body vazio")
    public void validoOCardCriadoComFalhaBodyVazio() {
        StatementCard.validCreateCardBodyEmptyFail(response);
    }

    @Quando("edito o card {string}")
    public void editoOCard(String type) {
        response = StatementCard.update(type, response);
    }

    @Entao("valido o card editado com sucesso")
    public void validoOCardEditadoComSucesso() {
        StatementCard.validUpdateCardSuccess(response);
    }

    @Entao("valido o card atualizado com body vazio")
    public void validoOCardAtualizadoComBodyVazio() {
        StatementCard.validUpdateCardBodyEmptySuccess(response);
    }

    @Entao("valido o card atualizado com falha de permissao de acesso")
    public void validoOCardAtualizadoComFalhaDePermissaoDeAcesso() {
        StatementCard.validUpdateCardNotPermissionFail(response);
    }

    @Quando("removo o card {string}")
    public void removoOCard(String type) {
        response = StatementCard.delete(type, response);
    }

    @Entao("valido o card removido com sucesso")
    public void validoOCardRemovidoComSucesso() {
        StatementCard.validDeleteCardSuccess(response);
    }

    @Entao("valido o card removido com falha nulo")
    public void validoOCardRemovidoComFalhaNulo() {
        StatementCard.validDeleteCardNullFail(response);
    }

    @Entao("valido o card removido com falha de permissao de acesso")
    public void validoOCardRemovidoComFalhaDePermissaoDeAcesso() {
        StatementCard.validRemoveCardNotPermissionFail(response);
    }

    @Entao("valido o card criado com falha idList")
    public void validoOCardCriadoComFalhaIdList() {
        StatementCard.validCreateCardBodyIdListInvalid(response);
    }

    @Entao("valido o card removido com falha id invalido")
    public void validoOCardRemovidoComFalhaIdInvalido() {
        StatementCard.validDeleteCardBodyIdInvalid(response);
    }

    @After
    public void after(){
        new StatementCard().clearCard(idCard);
    }

}
