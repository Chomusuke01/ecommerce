
# E-commerce

A simple e-commerce project. The application allows users to create, get and delete carts as well as add products to the different carts. The application [automatically](https://github.com/Chomusuke01/ecommerce/blob/main/src/main/java/com/ob/ecommerce/service/CartCleanUpService.java) deletes inactive carts.

## Main Features

* **Create a cart:** Create a new cart
* **Get a cart:** Get a cart information given its ID
* **Add products:** Add new products to a cart
* **Delete a cart:** Delete a cart given its ID
* **Delete inactive carts**: The application will automatically delete inactive carts.

## Built With

* Java 17
* SpringBoot 3
* Maven

## Getting Started

### Prerequisites

To run this project, ensure that you have Java 17 and Maven installed on your system.

You can check the installed java version with the following command:

```sh
java --version
```

You can check if you have Maven installed with the following command:

```sh
mvn -v
```

### Installation

1. Clone the repo

    ```sh
    git clone https://github.com/Chomusuke01/ecommerce.git
    ```

2. Create jar file

    ```sh
    mvn clean install
    ```

3. Run

    ```sh
    java -jar target/ecommerce-0.0.1.jar
    ```
   
If preferred, you can download and run [this](https://github.com/Chomusuke01/ecommerce/releases/tag/v0.0.1) jar file to save the hassle of building it yourself (make sure you have Java 17 installed).

## Functionality

The functionality of this application is provided through several API endpoints. Below is a list of available endpoints and their respective functionalities:

### Create a cart

* POST resquest to the following url:

    ```sh
    http://localhost:8080/api/v1/cart
    ```

* Response:

    ```json
    {
        "id": 1,
        "products": []
    }
    ```

### Get cart information given its ID

* GET resquest to the following url:

    ```sh
    http://localhost:8080/api/v1/cart/2
    ```

* Response:

    ```json
    {
        "id": 2,
        "products": 
        [
            {
                "id": 1,
                "description": "Mouse",
                "amount": 12.95
            },
            {
                "id": 2,
                "description": "Keyboard",
                "amount": 20.95
            }
        ]
    }
    ```

### Delete a cart given its ID

* DELETE resquest to the following url:

    ```sh
    http://localhost:8080/api/v1/cart/2
    ```

* Empty response. HTTP OK code

### Add a product to a cart

* POST resquest to the following url:

    ```sh
    http://localhost:8080/api/v1/cart/1/products
    ```

* Request body:

    ```json
    [
        {
            "id": 1,
            "description": "Mouse",
            "amount": 12.95
        }
    ]
    ```

  If you want to add several products in the same request, just add it to the array.

* Response:

    ```json
    {
        "id": 1,
        "products": 
        [
            {
                "id": 1,
                "description": "Mouse",
                "amount": 12.95
            }
        ]
    }
    ```

