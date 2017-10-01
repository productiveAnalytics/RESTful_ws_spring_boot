User Postman app / plugin

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
