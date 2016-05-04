# Shopping Cart

O projeto foi desenvolvido tendo como base o Spring Boot. Para rodá-lo, executar o seguinte comando: mvn clean package spring-boot:run
Após a execução do comando, a aplicação deve ser acessada neste endereço: http://localhost:8080

A aplicação foi testada apenas no Google Chrome.

## API REST

Foram criados dois controllers para a exposição dos quatro serviços REST: um para o serviço dos produtos e o outro para os serviços do carrinho.
O acesso à parte de negócio disparada pelos serviços do carrinho, só será possível quando for informado o usuário (devido à regra de um carrinho por user session). Na prática, este usuário nada mais é do que o request-header Cookie que deve ser enviado em todas as requisições. Portanto, caso a API seja testada sem que as requisições partam da própria aplicação, é necessário informar qualquer valor como Cookie no request-header destas requisições.

## Melhorias

Algumas melhorias sugeridas:
* Melhor detalhamento de logs;
* Testes de integração com base de dados em memória;
* Cobertura, via testes, de 100% das linhas de código que representam regras de negócio;
* Testes unitários da camada de controllers;
* Testar em outros browsers;
* Utilização do Bower para gerência de dependências do front-end;
* Utilização do Grunt para tarefas de front-end como minification e uglify.