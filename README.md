# E-Commerce Online Shop
E-Commerce online shop application on microservices architecture. Application builds docker images using Google Jib library.

# Config Server
1. Create public git repository.
2. Create files and name them same as microservices.
3. Paste following properties inside each file:
```dotenv
build:
  version: <your_version>

products:
  message: <some_message>
  contactDetails:
    name: "<your_name>"
    email: "<your_email>"
```
4. Install [HookDeck CLI](https://hookdeck.com/docs/cli#installation) and follow instructions
5. Run the `hookdeck listen 8070 --cli-path /monitor` command in your shell after you started application
6. Copy the link `hookdeck` generated
7. In config repository go to --> `Settings` --> `WebHooks` --> `Add webhook` and paste your link to `Payload URL` with Content type `application/json`
8. In `.env` file paste your URL with property name `GIT_CONFIG_URI`
> When you will make updates in your config files, it will automatically update application properties.

# How to run
1. Clone the repository
```bash
git clone https://github.com/timebetov/Spring-microservices-ecommerce.git
cd Spring-microservices-ecommerce
```
2. Create .env file in the project root with the following content:
```dotenv
CRYPTO_KEY=<your_crypto_key_with_encryption_cipher_AES_128_ECB>
```
3. Build and start all services with Makefile
```bash
make up
```
4. The application will be available at:
* http://localhost:8070/ - Config Server
* http://localhost:8080/products - Products Service
* http://localhost:8081/orders - Order Service
* http://localhost:8082/identity - Identity Service
* http://localhost:8083/basket - Basket Service
5. Test to get config properties:
```bash
curl -X GET http://localhost:8070/products/default
curl -X GET http://localhost:8070/order/default
curl -X GET http://localhost:8070/identity/default
curl -X GET http://localhost:8070/basket/default
```
6. Test certain microservice:
```bash
curl -X GET http://localhost:8080/products/contact-info
curl -X GET http://localhost:8081/orders/contact-info
curl -X GET http://localhost:8082/identity/contact-info
curl -X GET http://localhost:8083/basket/contact-info
```