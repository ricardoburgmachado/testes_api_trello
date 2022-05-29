# Testes de API TRELLO

Este projeto visa realizar a validação dos endpoints Trello com a responsabilidada de inclusão de um novo Card, edição de um Card e remoção de um Card.

Para isso foi criado um Board para prover um ID para ser utilizado nos eventos citados acima.

Além das validações de negócio, também foram incorporados testes de contrato, viabilizando a validação dinâmica de algumas estruturas de respostas JSON.

### Ferramentas, conceitos e linguagens utilizadas
- [x] Java
- [x] Maven
- [x] Rest Assured
- [x] Contract Test
- [x] Cucumber

### Execução do projeto com Maven
mvn clean test -Dexec.mainClass=br.com.runner.LocalRunnerTest -Dcucumber.options="--tags @Card"