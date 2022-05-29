#language: pt
#encoding: utf-8
@Card
Funcionalidade: Criação de um Card

  @createCard
  Cenario: Validar a criação de um novo Card com sucesso
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Entao valido o card criado com sucesso

  @createCard
  Cenario: Validar a criação de um novo Card vazio
    Dado que acesso a api da Trello
    Quando crio um novo card "FALHA_BODY_VAZIO"
    Entao valido o card criado com falha body vazio

  @createCard
  Cenario: Validar a criação de um novo Card com idList inválido
    Dado que acesso a api da Trello
    Quando crio um novo card "FALHA_ID_LIST"
    Entao valido o card criado com falha idList

  @createCard
  Cenario: Validar a criação de um novo Card sem permissão de acesso
    Dado que acesso a api da Trello
    Quando crio um novo card "FALHA_PERMISSAO_ACESSO"
    Entao valido o card criado com falha de permissao de acesso