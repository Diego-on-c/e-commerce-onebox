# E-commerce Cart Service

## Description

This project is a backend application for an e-commerce platform that manages shopping carts. The service allows creating (with automatic ID generation) and deleting carts, adding products to carts, and automatically removing inactive carts after 10 minutes. The application is developed in Java and uses Maven for dependency management.

## Requirements

- **Java 17**: Ensure you have Java 17 installed on your system.
- **Maven**: This project uses Maven as the build tool and dependency manager.
- **Docker**: Optional, if you want to build and run the application within a Docker container.
- **Postman**: For testing API endpoints.

## Project Setup

### Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/Diego-on-c/e-commerce-onebox
```

### Build the Project

Use Maven to build the project. This will compile the code and generate a JAR file in the `target` directory:

```bash
mvn clean install
```

### Run Tests

You can run the unit tests with the following command:

```bash
mvn test
```

### Run the Application

To run the application locally, use the following command:

```bash
java -jar target/e-commerce-0.0.1.jar
```

This will start the server on port 8080.

## Using the Service

### Available Endpoints

- **Create a cart**: `POST /carts`
- **Get cart information**: `GET /carts/{id}`
- **Update cart with new products**: `PUT /carts/{id}`
- **Delete a cart**: `DELETE /carts/{id}`

## Example with Postman

### Step-by-Step Instructions

1. **Create a Cart**

    - Open Postman and create a new `POST` request.
    - Enter the URL: `http://localhost:8080/carts`.
   - In the "Body" tab, select "raw" and "JSON" format.
   - Enter the following JSON data:
     ```json
     [
         {
             "id": 1,
             "description": "Product 1",
             "amount": 20.0
         },
         {
             "id": 2,
             "description": "Product 2",
             "amount": 10.0
         }
     ]
     ```
   - Click "Send".
    - The response will include the newly created cart's ID, which you can use in the following steps.

2. **Update a Cart**

    - Create a `PUT` request in Postman.
    - Enter the URL: `http://localhost:8080/carts/{id}`, replacing `{id}` with the ID you received in the previous step.
    - In the "Body" tab, select "raw" and "JSON" format.
    - Enter the following JSON data:

      ```json
      [
          {
              "id": 1,
              "description": "Product 1 Updated!",
              "amount": 2.0
          },
          {
              "id": 2,
              "description": "Product 2 Updated!",
              "amount": 1.0
          }
      ]
      ```
    - Click "Send".
    - The response will confirm that the products were added to the cart.

3. **Get Cart Information**

    - Create a new `GET` request in Postman.
    - Enter the URL: `http://localhost:8080/carts/{id}`, replacing `{id}` with your cart ID.
    - Click "Send".
    - The response will show the details of the cart, including the products added.

4. **Delete a Cart**

    - Create a new `DELETE` request in Postman.
    - Enter the URL: `http://localhost:8080/carts/{id}`, replacing `{id}` with your cart ID.
    - Click "Send".
    - The response will confirm that the cart has been deleted.

## Technical Implementation

### ID Generation

Cart IDs are generated using a method that creates an auto-incremental ID.

### Cart Expiration

Carts are automatically deleted after 10 minutes of inactivity. This is managed by an internal scheduler that checks and cleans up inactive carts.

### Data Storage

Carts are stored in memory using a volatile data structure (`HashMap`). No database engine is used, so data is lost upon application restart.

## Dockerization

### Dockerfile

The project includes a `Dockerfile` that allows you to build and run the application within a Docker container.

#### Build the Docker Image

```bash
docker build -t ecommerce:latest .
```

#### Run the Docker Container

```bash
docker run -p 8080:8080 ecommerce:latest
```

This will expose the application on port 8080 of your local machine.
