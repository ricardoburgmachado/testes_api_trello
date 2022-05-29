#language: pt
#encoding: utf-8
@Card
Funcionalidade: Remoção de um Card

  @removeCard
  Cenario: Validar a remoção de um Card com sucesso
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando removo o card "SUCESSO"
    Entao valido o card removido com sucesso

  @removeCard
  Cenario: Validar a remoção de um Card nulo
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando removo o card "NULL"
    Entao valido o card removido com falha nulo

  @removeCard
  Cenario: Validar a remoção de um Card com id inválido
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando removo o card "ID_INVALIDO"
    Entao valido o card removido com falha id invalido

  @removeCard
  Cenario: Validar a remoção de um novo Card sem permissão de acesso
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando removo o card "FALHA_PERMISSAO_ACESSO"
    Entao valido o card removido com falha de permissao de acesso