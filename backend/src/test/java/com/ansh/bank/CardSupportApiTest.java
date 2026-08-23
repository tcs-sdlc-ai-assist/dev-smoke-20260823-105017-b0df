package com.ansh.bank;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

/** Verify card controls, statement delivery, and support ticket contracts. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardSupportApiTest {
  @LocalServerPort int port;
  @Autowired TestRestTemplate rest;

  /** Authenticate the demo customer for protected resource assertions. */
  private HttpHeaders headers() {
    ResponseEntity<Map> login = rest.postForEntity(url("/api/auth/login"), Map.of("email", "demo@ansh.bank", "password", "demo123"), Map.class);
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth((String) login.getBody().get("token"));
    return headers;
  }

  /** Block a card and create a customer support ticket. */
  @Test void blocksCardAndCreatesTicket() {
    HttpHeaders headers = headers();
    assertEquals(200, rest.exchange(url("/api/cards/card-1/block"), HttpMethod.POST, new HttpEntity<>(headers), Map.class).getStatusCode().value());
    ResponseEntity<Map> ticket = rest.exchange(url("/api/support/tickets"), HttpMethod.POST, new HttpEntity<>(Map.of("subject", "Card question", "message", "Please call me."), headers), Map.class);
    assertEquals(200, ticket.getStatusCode().value());
    assertEquals("OPEN", ticket.getBody().get("status"));
  }

  /** Return a downloadable CSV statement for an owned account. */
  @Test void downloadsStatement() {
    ResponseEntity<String> statement = rest.exchange(url("/api/statements/csv"), HttpMethod.GET, new HttpEntity<>(headers()), String.class);
    assertEquals(200, statement.getStatusCode().value());
    assertTrue(statement.getBody().contains("Description"));
    assertTrue(statement.getHeaders().getContentType().toString().contains("text/csv"));
  }

  /** Build a server URL. */
  private String url(String path) { return "http://localhost:" + port + path; }
}
