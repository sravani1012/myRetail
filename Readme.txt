Technologies:
=============

Java Version- 1.8
SpringBoot - provides a container to run the application and Tomcat Server(Version : 2.0.0.RELEASE)
Spring Web Mvc- Spring framework to implement applications REST behaviors
Mongo Template- Java to Mongo connectivity.
Mockito - Implements automated integration tests
MockMvc - Integration tests
Jacoco - Code Coverage Analysis.
Log4j - Logging the data.
Maven - Automatic Build Script.
Databases - MongoDB(NoSQL)
GitHub - Repository
RestTemplate - Consume other end points from application.
RestClient - Postman
Python - Data Load
Intellij - IDE

Code overview:
=============
The source is broken into 2 parts

main -contains the application implementation.
test - contains unit and integration tests of the application.

The  implementation is broken into 6 distinct packages:

1. requestDTO  - Defines the input objects.
2. responseDTO - Defines the response objects.
3. dao - Implements the layer that will manage db access and queries using mongoTemplate.
4. service - Application layer that encapsulates business logic
5. controller - Application later that implement the service endpoint interfaces
6. exception - Implements custom exception handling.
7. utils - Handles the reusable/common code.
8. config - Maintains all the configurations.


Instructions for running the application:
=========================================
1. Download and install MongoDB.
2. Create a database named "myRetail" with collection as "products".
3. Download and install python.
4. Run the python script attached.
5. Import the java project into Intellij and clean build the project. It downloads all the dependencies
and runs the test case(mvn clean install).
6. Run the application as local profile(-Dspring.profiles.active=local)

Enpoints:
========

1. Get Product Information:
---------------------------

HttpMethod: Get
Url: /products/{id}
Sample Response:
{
	"id": Long
	"name": String,
	"current_price": {
		"value": Double,
		"currency_code": String
	}
}

2. Get product name by making a call to endpoint:
-------------------------------------------------


HttpMethod: Get
Url: /products/product_name/{id}
Sample Response: String


3. Get the product information by making a call to end point and price information from data store:
---------------------------------------------------------------------------------------------------

HttpMethod: Get
Url: /products/product_details/{id}
Sample Response:
{
	"id": Long
	"name": String,
	"current_price": {
		"value": Double,
		"currency_code": String
	}
}

4. Update the product information in the data store:
----------------------------------------------------

HttpMethod: PUT
Url: /products/update_product_details
Sample PUT Body:
{
	"id": Long
	"name": String,
	"current_price": {
		"value": Double,
		"currency_code": String
	}
}

Sample Response:
{
	"id": Long
	"name": String,
	"current_price": {
		"value": Double,
		"currency_code": String
	}
}

5. Get multiple products in one call:
-------------------------------------

HttpMethod: POST
Url: /products/get_multiple_products
Sample POST Body:
{
  "products": [Long]
}

Sample Response:

[{
	"id": Long
	"name": String,
	"current_price": {
		"value": Double,
		"currency_code": String
	}
}]
