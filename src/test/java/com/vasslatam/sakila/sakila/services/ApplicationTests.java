package com.vasslatam.sakila.sakila.services;

import com.vasslatam.sakila.domain.Actor;
import com.vasslatam.sakila.endpoint.request.ActorRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

    @LocalServerPort
    int randomServerPort;

    @Test
    @Order(1)
    void addActorTest() throws URISyntaxException {
        RestTemplate client = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/actor/";
        URI uri = new URI(baseUrl);
        ActorRequest actorRequest = new ActorRequest("Juan", "Perez");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ActorRequest> request = new HttpEntity<>(actorRequest, headers);
        ResponseEntity<Actor> response = client.postForEntity(uri, request, Actor.class);
        assertEquals(200, response.getStatusCodeValue());
        LOGGER.info("body:{}", response.getBody());
    }

    @Test
    @Order(2)
    void actorTest() throws URISyntaxException {
        RestTemplate client = new RestTemplate();
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/actor/";
        URI uri = new URI(baseUrl);
        ResponseEntity<List> response = client.getForEntity(uri, List.class);
        assertEquals(200, response.getStatusCodeValue());
        if (response.getBody() != null) {
            List<Map<String, ?>> list = response.getBody();
            if (list != null) {
                list.forEach((item) -> {
                    LOGGER.info("\t- {}", item);
                });
            }
        }
    }

}
