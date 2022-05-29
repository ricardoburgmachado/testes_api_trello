#language: pt
#encoding: utf-8
@Card
Funcionalidade: Edição de um Card

  @updateCard
  Cenario: Validar a criação de um novo Card com sucesso
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando edito o card "SUCESSO"
    Entao valido o card editado com sucesso

  @updateCard
  Cenario: Validar a atualização de um Card vazio com sucesso
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando edito o card "BODY_VAZIO"
    Entao valido o card atualizado com body vazio

  @updateCard
  Cenario: Validar a criação de um novo Card sem permissão de acesso
    Dado que acesso a api da Trello
    Quando crio um novo card "SUCESSO"
    Quando edito o card "FALHA_PERMISSAO_ACESSO"
    Entao valido o card atualizado com falha de permissao de acesso