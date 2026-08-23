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

/** Verify beneficiary and transfer contracts across the real HTTP application. */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferBeneficiaryApiTest {
  @LocalServerPort int port;
  @Autowired TestRestTemplate rest;

  /** Authenticate and return the bearer headers for protected contract tests. */
  private HttpHeaders headers() {
    ResponseEntity<Map> login = rest.postForEntity(url("/api/auth/login"), Map.of("email", "demo@ansh.bank", "password", "demo123"), Map.class);
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth((String) login.getBody().get("token"));
    return headers;
  }

  /** Add, verify, and use a beneficiary for a live transfer. */
  @Test void createsBeneficiaryAndTransfersFunds() {
    HttpHeaders headers = headers();
    ResponseEntity<Map> beneficiary = rest.exchange(url("/api/beneficiaries"), HttpMethod.POST, new HttpEntity<>(Map.of("name", "Jordan Lee", "accountNumber", "887766", "bank", "ANSH"), headers), Map.class);
    assertEquals(200, beneficiary.getStatusCode().value());
    String id = (String) beneficiary.getBody().get("id");
    assertEquals(200, rest.exchange(url("/api/beneficiaries/" + id + "/verify"), HttpMethod.POST, new HttpEntity<>(headers), Map.class).getStatusCode().value());
    ResponseEntity<Map> transfer = rest.exchange(url("/api/transactions/transfer"), HttpMethod.POST, new HttpEntity<>(Map.of("accountId", "acc-1", "beneficiaryId", id, "amount", 25.50, "reference", "Test"), headers), Map.class);
    assertEquals(200, transfer.getStatusCode().value());
    assertTrue(String.valueOf(transfer.getBody().get("description")).contains("Jordan Lee"));
  }

  /** Reject a transfer that exceeds the available balance. */
  @Test void rejectsInsufficientFunds() {
    HttpHeaders headers = headers();
    ResponseEntity<String> result = rest.exchange(url("/api/transactions/transfer"), HttpMethod.POST, new HttpEntity<>(Map.of("accountId", "acc-1", "beneficiaryId", "ben-1", "amount", 999999, "reference", "Test"), headers), String.class);
    assertEquals(400, result.getStatusCode().value());
  }

  /** Build a server URL. */
  private String url(String path) { return "http://localhost:" + port + path; }
}
