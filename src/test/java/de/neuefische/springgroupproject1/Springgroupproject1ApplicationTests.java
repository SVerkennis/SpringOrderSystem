package de.neuefische.springgroupproject1;

import de.neuefische.springgroupproject1.db.OrderDB;
import de.neuefische.springgroupproject1.db.ProductDB;
import de.neuefische.springgroupproject1.model.Order;
import de.neuefische.springgroupproject1.model.Product;
import de.neuefische.springgroupproject1.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Springgroupproject1ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    // https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/web/client/TestRestTemplate.html
    private TestRestTemplate restTemplate;

    @Test
    public void listProductsShouldReturnAllProducts(){
        //GIVEN


        //WHEN
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:"+ port + "/products", Product[].class);
        HttpStatus statusCode = response.getStatusCode();
        Product[] products = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(4, products.length);
    }

    @Test
    public void listOrdersShouldReturnAllOrders() {
        public void putStudentShouldAddStudentToDatabase() {
            //GIVEN
            HttpEntity<Order> requestEntity = new HttpEntity<>(new Order("5", "LeckerPizza"));

            //WHEN
            ResponseEntity<Order> postResponse = restTemplate.exchange("http://localhost:" + port + "/students", HttpMethod.PUT, requestEntity, Order.class);

            //THEN
            assertEquals(HttpStatus.OK, postResponse.getStatusCode());
            assertEquals(new Order("1", "LeckerPizza"), postResponse.getBody());
            assertTrue(OrderDB.addOrders().contains(new Order("1", "LeckerPizza")));
        }




        //Post
        ArrayList<Product> artNo = new ArrayList<>();
        ResponseEntity<Order> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/orders", new Product("3","Affe"), Order.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        //WHEN
        ResponseEntity<Order[]> response = restTemplate.getForEntity("http://localhost:" + port + "/products", Order[].class);
        HttpStatus statusCode = response.getStatusCode();
        Order[] orders = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(2, orders.length);
    }


}
