

RESTful API to simulate simple banking operations. 

## Facilities

*	JPA CRUD operations for customers and accounts.
*	Support deposits and withdrawals on accounts.[http://localhost:9090/accounts/transfer/deposite/564561631367?amount=522] [http://localhost:9090/accounts/transfer/withdrawal/564561631367?amount=200]
*	Internal transfer support (i.e. a customer may transfer funds from one account to another).[http://localhost:9090/accounts/transfer/{accountNumber}]
*	Add, Find, Update and Delete Customer details. Add[http://localhost:9090/customers/add] Retrive[http://localhost:9090/customers/find/{customerNumber}] Update[http://localhost:9090/customers/update/{cusomerNumber}] Delete[http://localhost:9090/customers/delete/{customerNumber}]
*	View transcation details[http://localhost:9090/accounts/transactions/{accountNumber}]
*	Get all account details[http://localhost:9090/accounts/all] and details by id [http://localhost:9090/accounts/{564561631367}]
*	Get all customer details [http://localhost:9090/customers/all]
*	View remains balance[http://localhost:9090/accounts/transactions/balance/{accountNumber}]
*	Link customer with accounts [http://localhost:9090/accounts/add/{customerNumber}]


## Port Number
 Default port for the api is 9090
	

### Dependencies

```

spring-boot-starter-data-jpa
spring-boot-starter-web
spring-boot-devtools
h2 - Inmemory database
lombok 
springfox-swagger2
springfox-swagger-ui
spring-boot-starter-test
junit-jupiter-api

```

## Swagger

Please find the Rest API documentation in the below url

```
http://localhost:9090/swagger-ui.html#/

```

## H2 In-Memory Database

jdbc:h2:mem:testdb as your jdbc url

```
http://localhost:9090/h2

```

