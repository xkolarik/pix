# Projeto Pix com Spring Boot e H2 Database

Este é um projeto de exemplo utilizando Java 17, Spring Boot, Maven, e H2 Database. 
O projeto segue o padrão MVC e princípios SOLID.

## Pré-requisitos

- JDK 17 ou superior
- Maven 3.6.x ou superior

## Configuração do Projeto

Clone o repositório:

```bash
git clone https://github.com/pix.git
cd pix

```

Build do Projeto:

Para compilar o projeto, execute o seguinte comando:

```bash
mvn clean install
mvn spring-boot:run

```
O aplicativo estará disponível em http://localhost:8080.

## Banco de Dados H2
O banco de dados H2 é utilizado neste projeto. Acesse o console do H2 em http://localhost:8080/h2-console.

JDBC URL: jdbc:h2:mem
Username: sa
Password: 1234

## Testando a API
Utilize o Postman ou qualquer cliente REST para testar os endpoints. Exemplos de endpoints:

POST http://localhost:8080/conta: Cria uma nova conta. 

POST http://localhost:8080/transacao: Realizar operação financeira

GET http://localhost:8080/conta?numero_conta=234: Busca uma conta pelo número da conta.


## Documentação da API
A documentação da API pode ser feita utilizando o Swagger.

Acesse a documentação em http://localhost:8080/swagger-ui/index.html.

