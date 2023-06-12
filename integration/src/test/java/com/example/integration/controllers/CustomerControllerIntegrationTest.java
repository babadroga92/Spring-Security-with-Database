package com.example.integration.controllers;

import com.example.integration.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCustomerService() {
        List<Customer> customers = testRestTemplate.getForObject("http://localhost:" + port + "/customers", List.class);
        Assertions.assertNotNull(customers);
        Assertions.assertEquals(2, customers.size());

    }
}
