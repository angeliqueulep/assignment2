# **Enterprise Appl Modelling - ITE-5435-IRA**
## Assignment 2 - E-commerce Microservice Application Project
This project aims to build a basic e-commerce application using microservices architecture with Spring Framework in Java. Containerization with Docker and communication via Kafka will be implemented.

## Members:
1. Angelique Ulep
2. Gem Robino
3. Ryan Shum

## Running the Application
1. Run all Services:
```
  - EurekaService
  - ImageService
  - ProductServices
  - OrderServices
  - AdminServices
  - Frontend
```
2. Navigate to `localhost:8080`

<hr>

# E-commerce Microservice Application API Documentation

## Product Service

The product service microservice manages the product-related operations including creating, retrieving, updating, and deleting product information. It interacts with the various classes to perform these operations and communicates responses back to the client.

**Base URL:** `/products`

#### Fetch All Products

- **Endpoint:** `GET /products`
- **Description:** Retrieves a list of all products available in the database.
- **Response:** Returns a list of products in the form of `ProductDTO` objects.

#### Create a New Product

- **Endpoint:** `POST /products`
- **Description:** Adds a new product to the database.
- **Request Body:** Requires a `ProductDTO` object.
- **Response:** Returns the created `ProductDTO` object with a 201 status code.

#### Get Product by ID

- **Endpoint:** `GET /products/{id}`
- **Description:** Fetches a single product based on its ID.
- **Path Variable:** `id` - The ID of the product to retrieve.
- **Response:** Returns the requested `ProductDTO` object if found.

#### Delete a Product

- **Endpoint:** `DELETE /products/{id}`
- **Description:** Removes a product from the database based on its ID.
- **Path Variable:** `id` - The ID of the product to delete.
- **Response:** Returns a 204 status code if successful.

#### Update a Product

- **Endpoint:** `PUT /products/{id}`
- **Description:** Updates the details of an existing product.
- **Path Variable:** `id` - The ID of the product to update.
- **Request Body:** Requires a `ProductDTO` object with updated fields.
- **Response:** Returns the updated `ProductDTO` object if successful.

#### Update Product Stock

- **Endpoint:** `PUT /products/{id}/updateStock`
- **Description:** Updates the stock quantity of a specific product.
- **Path Variable:** `id` - The ID of the product for which stock is updated.
- **Request Body:** Requires an integer value representing the amount to be added or subtracted from the current stock of the product.
- **Response:** Returns the updated `ProductDTO` object if successful.

### Admin Service

The admin service microservice is responsible for the administrative operations regarding products and orders.

**Base URL:** `/admin`

#### Product Management Endpoints

#### Fetch All Products

- **Endpoint:** `GET /admin/products`
- **Description:** Retrieves a list of all products.
- **Response:** Returns a list of `ProductDTO` objects.

#### Create a New Product

- **Endpoint:** `POST /admin/products`
- **Description:** Adds a new product to the inventory.
- **Request Body:** `ProductDTO` object containing product details.
- **Response:** Returns the created `ProductDTO`.

#### Get Product by ID

- **Endpoint:** `GET /admin/products/{id}`
- **Description:** Fetches details of a specific product using its ID.
- **Path Variable:** `id` - Product ID.
- **Response:** Returns `ProductDTO` of the specified product.

#### Update a Product

- **Endpoint:** `PUT /admin/products/{id}`
- **Description:** Updates an existing product's details.
- **Path Variable:** `id` - Product ID.
- **Request Body:** `ProductDTO` with updated fields.
- **Response:** Returns the updated `ProductDTO`.

#### Delete a Product

- **Endpoint:** `DELETE /admin/products/{id}`
- **Description:** Removes a product from the inventory using its ID.
- **Path Variable:** `id` - Product ID.
- **Response:** No content (204 status code) returned upon successful deletion.

#### Order Management Endpoints

#### Fetch All Orders

- **Endpoint:** `GET /admin/orders`
- **Description:** Retrieves a list of all orders placed.
- **Response:** Returns a list of `OrderDTO` objects.

#### Get Order by ID

- **Endpoint:** `GET /admin/orders/{id}`
- **Description:** Fetches details of a specific order using its ID.
- **Path Variable:** `id` - Order ID.
- **Response:** Returns `OrderDTO` of the specified order.

### Orders Service

The order service manages all the functionalities related to orders within the e-commerce application. This includes creating, retrieving, updating, and deleting orders as well associating order items to it. It uses StreamBridge to send events to other microservices for notification about order placement.

**Base URL:** `/orders`

#### Fetch All Orders

- **Endpoint:** `GET /orders`
- **Description:** Retrieves a list of all orders processed by the system.
- **Response:** Returns a list of `OrderDTO` objects representing the orders.

#### Create a New Order

- **Endpoint:** `POST /orders`
- **Description:** Allows the creation of a new order in the system.
- **Request Body:** Requires an `OrderDTO` object with order details.
- **Response:** Returns the newly created `OrderDTO` object and sends an event to the product service via StreamBridge.

#### Get Order by ID

- **Endpoint:** `GET /orders/{id}`
- **Description:** Fetches a specific order by its ID.
- **Path Variable:** `id` - The ID of the order to retrieve.
- **Response:** Returns an `OrderDTO` object if found, or a 404 error if not found.

#### Update an Order

- **Endpoint:** `PUT /orders/{id}`
- **Description:**

 Updates an existing order's details.
- **Path Variable:** `id` - The ID of the order to update.
- **Request Body:** `OrderDTO` object with updated details.
- **Response:** Returns the updated `OrderDTO` object, or a bad request error if the update fails.

#### Delete an Order

- **Endpoint:** `DELETE /orders/{id}`
- **Description:** Removes an order from the system using its ID.
- **Path Variable:** `id` - The ID of the order to delete.
- **Response:** Returns a no content status (204) if successful, or a not found error if the order does not exist.

### Image Service

The image service handles operations related to image management in the e-commerce app. This includes fetching, listing, and adding images.

**Base URL:** `/images`

#### List All Images

- **Endpoint:** `GET /images/list`
- **Description:** Retrieves a list of all images stored in the system.
- **Response:** Returns a list of `Image` objects, each representing an image.

#### Get Image by ID

- **Endpoint:** `GET /images/{id}`
- **Description:** Fetches the filepath of a specific image by its ID.
- **Path Variable:** `id` - The ID of the image to retrieve.
- **Response:** Returns a string representing the image's file path, or an empty string if the image is not found.

#### Create a New Image

- **Endpoint:** `POST /images/create`
- **Description:** Adds a new image to the database.
- **Request Body:** Requires an `Image` object containing the image details.
- **Response:** Returns a confirmation message with the newly created image ID, or an error message if the creation fails.


<hr>

## Microservices

### Products Service
The Products Service manages product information and provides endpoints for CRUD operations on products.

#### Key Dependencies:
- Spring Cloud Stream Binder Kafka
- Spring Cloud Starter Netflix Eureka Client
- Flyway Core and Flyway MySQL
- Spring Cloud Starter OpenFeign

### Admin Service
The Admin Service manages admin operations and provides endpoints for CRUD operations on products and orders.

#### Key Dependencies:
- Spring Cloud Starter OpenFeign
- Spring Cloud Starter Netflix Eureka Client
- MySQL Connector/J

### Orders Service
The Orders Service processes customer orders and provides an endpoint for creating new orders.

#### Key Dependencies:
- Spring Cloud Stream Binder Kafka
- Spring Cloud Starter Netflix Eureka Client
- MySQL Connector/J

### Image Service
The Image Service creates product images and retrieves them based on image ID.

#### Key Dependencies:
- Spring Cloud Stream Binder Kafka
- Spring Cloud Starter Netflix Eureka Client
- H2 Database

### Edge Server
The Edge Server acts as an API Gateway, managing external requests and routing them to the appropriate microservice.

#### Key Dependencies:
- Spring Cloud Starter Circuit Breaker with Resilience4J
- Spring Cloud Starter Gateway
- Spring Cloud Starter Netflix Eureka Client

### Eureka Service
The Eureka Service acts as a registry for all the microservices in the application. It allows services to register themselves and query for other services.

#### Key Dependencies:
- Spring Cloud Starter Netflix Eureka Server
