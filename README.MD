# pub-future-api

## About the API

An API for person finances. It is built with Java, Spring Boot, and MySQL.

## Features

This API provides HTTP endpoint's and tools for the following:

Accounts:
* Create an account: `POST/http://localhost:8080/contas'
* List all accounts: `GET/http://localhost:8080/contas'
* Update an account: `PUT/http://localhost:8080/contas/{codConta}'
* Delete an account: `DELETE/http://localhost:8080/contas/{origem}/{destino}/{valor}'
* Transfer funds between accounts: `PUT/http://localhost:8080/contas/{codConta}'
* List total money avaliable by type of account: `GET/http://localhost:8080/contas/totalContas'

Expenses:
* Create an expense: `POST/http://localhost:8080/despesas'
* List all expenses: `GET/http://localhost:8080/despesas'
* Update an expense: `PUT/http://localhost:8080/despesas/{codDespesa}'
* Delete an expense: `DELETE//http://localhost:8080/despesas/{codDespesa}'
* List expenses by type: `GET/http://localhost:8080/despesas/{tipoDespesa}'
* List total amount of expenses: `GET/http://localhost:8080/despesas/totalDespesas'
* List expenses by period: `GET/http://localhost:8080/despesas/buscarPorPeriodo?dataInicial=yyyy-MM-dd&dataFinal=yyyy-MM-dd'

Incomes:
* Create an income: `POST/http://localhost:8080/receitas'
* List all incomes: `GET/http://localhost:8080/receitas'
* Update an income: `PUT/http://localhost:8080/receitas/{codReceita}'
* Delete an income: `DELETE//http://localhost:8080/receitas/{codReceita}'
* List incomes by type: `GET/http://localhost:8080/receitas/{tipoReceita}'
* List total amount of incomes: `GET/http://localhost:8080/receitas/totalReceitas'
* List incomes by period: `GET/http://localhost:8080/receitas/buscarPorPeriodo?dataInicial=yyyy-MM-dd&dataFinal=yyyy-MM-dd'



### Technologies used

This project was developed with:

* **Java 17 LTS - 2021-09-14**
* **Spring Boot 2.6.2**
* **Spring DATA-JPA 2.6.2**
* **Spring Fox 3.0.0**
* **Maven**
* **MySQL 8.0.25**
* **Lombok**

### Execution
-bash/

git clone https://github.com/v-venturi/DesafioPubFuture.git
- You need to have **MySQL** installed on your machine to run the API.
- You don't need to create the database, **Hibernate** create its following the Classes and relatioships.
- Despite being developed with Java 17, you can run it on any Java 11+.

* **Just remember to change the database password in src/main/resources/application.yml**


### Documentation

* **Swagger** http://localhost:8080/swagger-ui/

* **Postman** collection link to test the API 
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1a5f9d26fe3ed322ee5b?action=collection%2Fimport)




