## Food Delivery Application
Spring Boot Application based on microservices architecture.

- Interprocess Communication between microservices.
- Service Discovery using netflix eureka server.
- Api gateway using spring cloud gateway.
- All the api requests are authenticated using spring security and jwt token.
- Event Driven Architecture using KAFKA.

### Functionalities

* User can register as a CUSTOMER or RESTAURANT_OWNER or DELIVERY_AGENT or ADMIN.
* User can add a new restaurant and fooditems. 
* RESTAURANT_OWNER can update food items. User can see all food items offered by a restaurant.
* User can place an order. quantity of the ordered items will be updated. 
* After order status is confirmed a notification will be sent to the corresponding restaurant for preparing the order.
* User can make payment. After payment is done order status will be updated as confirmed and a notification will be sent to order-service.


**TO DO**
- [ ] Implement Delivery Service. Take delivery distance, restaurant hours into consideration.
- [ ] Return appropriate error responses and codes.
- [ ] Implement Circuit Breaker.
- [ ] Implement micrometer for tracing
- [ ] Handle role based authorization.

<!---

### Discovery Server
```
Host all the microservices on Eureka Server.
```

### API Gateway
```
Runs on port 8080. Forward all the api requests from user to the destination microservice.
Filter all the http requests using Authorization token. 
```

### Restaurant Service
```
Handles restaurants and food items.
Restaurant owner can add a new restaurant. Can add and update food items for corresponding restaurant.
```

### Order Service
```
Handle orders. User can place an order. 
Verifies the order request. Notify restaurant service after a new order is placed and confirmed
```

### Payment Service
```
Handle payments.
Notify order service after payment is done.
```
-->