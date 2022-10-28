# OnlineBank


## Desenvolvimento


A Aplicação fica disponivel em: `localhost:8080`

Swagger :`http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs`

## Build

O build da aplicação é realizado com o seguinte comando:

```
mvnw clean package
```

## Base de dados
Iniciei o projeto com o H2 database para a fase de prototipação e testes, a intenção e trocar para MySQL 
no futuro, foi utilizado tambem o Hibernate para mapear as entidades e o JPA para gerenciar os repositorios
e gerar queries automaticamente

A aplicação gerará automaticamente uma pessoa com Id 10 e uma conta com id 11 para agilizar os testes.

## Testes
As classes de teste foram criadas e até o momento todos os services e controllers são testados quanto a criação, os testes serão aumentados no
futuro
## Descrição dos endpoints
### Contas

#### /api/conta/bloquear/{idConta} 
Bloqueia a conta  com o id recebido
#### /api/conta/desbloquear/{idConta}
Desbloqueia a conta com o id recebido

#### /api/conta/consultarTodas
retorna todas as contas

#### /api/conta/ConsultarSaldo/{idConta}
retorna o saldo da conta com o id recebido

### Transaçoes
#### /api/transacoes/realizarTransacao
Como no bando de dados não há nenhuma diferenciação sobre o tipo de transação, optei por criar somente um endpoint para saque e
deposito, onde o que deferencia e o valor da transação

#### /api/transacoes/extrato/{idConta}

### Crud de Pessoas
apesar de não ser um requisito para o sistema, o JPA torna muito simples a criação de um Crud e optei por deixa-lo implementado
#### /api/conta/ConsultarSaldo/{idConta}
retorna uma lista com todas as transaçoes de uma conta 
limitadas pela data inicial e final que devem ser passados no corpo da requisição, 
as datas são opcionais e caso não sejam preenchidads o sistema retornará todas as transaçoes

#### /api/pessoas/criar 
cria uma pessoa e retorna o id gerado automaticamente

#### /api/pessoas/consultarTodas
Consulta todas as pessoas cadastradas

#### /api/pessoas/consultar/{idPessoa}
Consulta uma pessoa com o id passado

#### /api/pessoas/deletar/{idPessoa}
deleta a pessoa com o id passado

