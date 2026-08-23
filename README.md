# Ansh Bank

A runnable online-banking customer portal built from the supplied HLD and LLD.

## Stack
- Java 17 / Spring Boot 3 REST API with JWT-style bearer authentication and an in-memory development data store.
- React + TypeScript + Vite customer SPA using Axios and a dev proxy to the API.
- JUnit API tests, Vitest component tests, and Playwright live browser journeys.

## Included customer flows
- Demo login and protected account dashboard.
- Beneficiary creation and verification, transfer submission, and transaction history.
- Card blocking/unblocking, CSV statement download, and customer support tickets.

## Demo access
Use **demo@ansh.bank** and **demo123**.

## Run locally
```bash
mvn -f pom.xml spring-boot:run -Dspring-boot.run.arguments='--server.port=8080'
cd frontend
npm install --no-bin-links
node node_modules/vite/bin/vite.js --port 5173
```
Open `http://localhost:5173`.

## Verify
```bash
mvn -f pom.xml test
cd frontend && node node_modules/vitest/vitest.mjs run
mvn -f pom.xml package -DskipTests
cd frontend && node node_modules/vite/bin/vite.js build
# With both servers running:
cd frontend && node node_modules/@playwright/test/cli.js test --config=playwright.config.ts
```

## Development architecture
The Spring Boot code is organized into feature-oriented controller, service, DTO, and security packages. The production HLD calls for MongoDB, Redis, and Kafka; this sandbox-ready implementation deliberately uses deterministic in-memory data and has no embedded infrastructure credentials. It retains the HTTP contracts and layering so external persistence and messaging adapters can be introduced safely.
