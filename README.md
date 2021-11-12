# Simple Microservices - DDD
#### Technologies:
1. framework: J2EE + Spring Boot(web,jpa,validation)
2. technology: Restfull Api
3. documentation: doc-openapi3(swagger)
4. DB: MySQl, H2
5. MQ: RabbitMq
6. IDE: Intellij IDE

#### Business:
E-Commerce Online-shopping with following Microservices:
1. People microservice: Keep the customer information
2. Product microservice: Keep the product information
3. Shopping microservice: Keep buy information for each customer
4. Finance microservice: Finalize a cart and payment

#### Main Process:
1. Shopping: Get products from Product via microservice
2. Shopping: Add Product to cart(h2 db)
3. Shopping: Close cart(Add to paymentMq)
4. Payment: Payed the order:
4.1  check the quantity of each product
4.2  add the products which in the cart in ProductMQ for removing the count from Inventory  
5. Product: Read from ProductMQ to eliminate product from Inventory
6. Payment: Call carry webservice to carry the order.


