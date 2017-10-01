## Starting the Order Management System
To start this web service, install [Maven](https://maven.apache.org/install.html) and execute the following command

    mvn spring-boot:run
    
Once the web service is started, it can be reached at

    http://localhost:8080/order

## REST Endpoints
The following REST endpoints are available upon deployment of the order management system:

| HTTP Verb        | URL           | Description  | Status Codes |
| ------------- |-------------|:-----| ----|
| `GET` | `http://localhost:8080/order` | Obtains a list of all existing orders | <ul><li>`200 OK`</li></ul> |
| `GET` | `http://localhost:8080/order/{id}` | Obtains the order corresponding to the supplied order ID | <ul><li>`200 OK` if order exists</li><li>`404 Not Found` if order does not exist</li></ul> |
| `POST` | `http://localhost:8080/order` | Creates a new order based on the payload contained in the request body | <ul><li>`201 Created` if order successfully created</li></ul> |
| `PUT` | `http://localhost:8080/order/{id}` | Updated an existing order with the data contained in the request body | <ul><li>`200 OK` if order succesfully updated</li><li>`404 Not Found` if order does not exist</li></ul> |
| `DELETE` | `http://localhost:8080/order/{id}` | Deletes an existing order that corresponds to the supplied order ID | <ul><li>`203 No Content` if order succesfully deleted</li><li>`404 Not Found` if order does not exist</li></ul> |



Test using Postman app / plugin

----

GET : localhost:8080/order
	
GET (by id) : localhost:8080/order/1

----
	
POST : localhost:8080/order
	Header = Content-Type: application/json
	Body (Use RAW format in Postman, instead of Key/Value) = 
		{
		"description":"Second order",
		"cost-in-cents":1500,
		"complete":false
		}
  Expected Result: 201 (Created)

----

PUT : localhost:8080/order/1
	Header = Content-Type: application/json
	Body (Use RAW format in Postman, instead of Key/Value) = 
		{
		"description":"Second order (updated)",
		"cost-in-cents":2500,
		"complete":true
		}
  Expected Result: 200 (Ok)

----
		
DELETE : localhost:8080/order/1
  Expected Result: 200 (Ok) if Delete successful
  Expected Result: 404 (Not Found) if re-Delete tried
	Expected result:
