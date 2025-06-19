# E-Commerce Online Shop
E-Commerce online shop application on microservices architecture. Application builds docker images using Google Jib plugin.

# Config Server
1. Create public git repository.
2. Create files and name them same as microservices.
3. Paste following properties inside each file:
```dotenv
build:
  version: <your_version>

<MICROSERVICE_NAME>:
  message: <some_message>
  contactDetails:
    name: "<your_name>"
    email: "<your_email>"
```
4. In `.env` file paste your URL with property name `GIT_CONFIG_URI`

## 1. Update properties manually
> You have to manually invoke `/busRefresh` in one of microservices after each update.

## 2. Update properties automatically
1. Install [HookDeck CLI](https://hookdeck.com/docs/cli#installation) and follow instructions
2. Run the `hookdeck listen 8070 --cli-path /monitor` command in your shell after you started application
3. Copy the link `hookdeck` generated
4. In config repository go to --> `Settings` --> `WebHooks` --> `Add webhook` and paste your link to `Payload URL` with Content type `application/json`
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
* http://localhost:8071/ - Eureka Server
* http://localhost:8080/ - Gateway Server
* http://localhost:8081/orders - Order Service
* http://localhost:8082/identity - Identity Service
* http://localhost:8083/basket - Basket Service
* http://localhost:8084/products - Products Service
* http://localhost:3000/ - Grafana
* http://localhost:9090/ - Prometheus
5. Test to get config properties:
```bash
curl -X GET http://localhost:8070/products/default
curl -X GET http://localhost:8070/order/default
curl -X GET http://localhost:8070/identity/default
curl -X GET http://localhost:8070/basket/default
```
6. Test certain microservice:
```bash
curl -X GET http://localhost:8080/ecommerce/orders/actuator/info
curl -X GET http://localhost:8080/ecommerce/identity/actuator/info
curl -X GET http://localhost:8080/ecommerce/basket/actuator/info
curl -X GET http://localhost:8080/ecommerce/products/actuator/info
```