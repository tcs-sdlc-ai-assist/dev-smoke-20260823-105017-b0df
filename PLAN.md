# Ansh Online Banking Platform — Master Plan

## Outcome
Build a responsive React customer banking portal backed by a Java 17 / Spring Boot REST API. Customers can sign in with seeded demo credentials, view accounts, transfer funds, manage beneficiaries and cards, view statements, and raise support tickets. The application uses an in-memory development profile so the complete system is runnable and testable in this sandbox while preserving the LLD's controller → service → repository → document/DTO boundaries.

## Upstream and decisions
This plan implements `/hld_architect/hld.md` and `/lld_architect/Ansh_LLD.md`; the PRD was not needed. The prescribed stack is React 18 + TypeScript, Spring Boot 3 / Java 17, and MongoDB in production. No sandbox MongoDB connection details were supplied, so the runnable dev/test profile uses repository interfaces backed by in-memory data; production Mongo/Redis/Kafka integration is intentionally external configuration rather than embedded credentials.

The UI uses the **design-modern** house style: a distinctive deep-teal banking palette, warm neutral surfaces, display/body type pairing, one focal account-balance card per view, responsive navigation, accessible controls, and runtime light/dark mode.

## File inventory
### Foundation and configuration
- `.gitignore` — excludes engine, dependencies, build artifacts, and secrets.
- `pom.xml` — Spring Boot, validation, security/JWT, and test dependencies.
- `backend/src/main/resources/application.yml` — safe externalized server and JWT defaults.
- `backend/src/main/java/com/ansh/bank/AnshBankApplication.java` — Spring application entry point.
- `backend/src/main/java/com/ansh/bank/common/security/SecurityConfig.java` — JWT request security configuration.
- `backend/src/main/java/com/ansh/bank/common/security/JwtService.java` — signed access-token creation and validation.
- `backend/src/main/java/com/ansh/bank/common/security/JwtAuthFilter.java` — request token authentication filter.
- `backend/src/main/java/com/ansh/bank/common/exception/ApiException.java` — typed API error.
- `backend/src/main/java/com/ansh/bank/common/exception/GlobalExceptionHandler.java` — standard error-envelope mapping.
- `backend/src/main/java/com/ansh/bank/common/config/SeedData.java` — seeded customer, accounts, card, and demo recipient.
- `frontend/package.json` — pinned React/Vite/test dependencies and scripts.
- `frontend/tsconfig.json` — TypeScript compiler options.
- `frontend/vite.config.ts` — Vite dev server proxy to the backend.
- `frontend/index.html` — font and initial theme bootstrap.
- `frontend/src/main.tsx` — React bootstrapping.
- `frontend/src/styles.css` — semantic light/dark token system and responsive global styling.
- `frontend/src/App.tsx` — providers and application router.
- `frontend/src/types.ts` — shared client DTO types.
- `frontend/src/api/client.ts` — Axios client with auth header/interceptor.
- `frontend/src/components/AppShell.tsx` — responsive header/sidebar, theme control, logout.
- `frontend/src/components/Ui.tsx` — shared button, card, field, status, alert, empty-state primitives.

### Auth and dashboard slice
- `backend/src/main/java/com/ansh/bank/auth/document/User.java` — user domain document.
- `backend/src/main/java/com/ansh/bank/auth/repository/UserRepository.java` — user lookup port.
- `backend/src/main/java/com/ansh/bank/auth/service/AuthService.java` — login and demo registration behavior.
- `backend/src/main/java/com/ansh/bank/auth/controller/AuthController.java` — auth REST endpoints.
- `backend/src/main/java/com/ansh/bank/auth/dto/AuthDtos.java` — auth request/response DTOs.
- `backend/src/main/java/com/ansh/bank/account/document/Account.java` — account domain document.
- `backend/src/main/java/com/ansh/bank/account/repository/AccountRepository.java` — account storage port.
- `backend/src/main/java/com/ansh/bank/account/service/AccountService.java` — account query behavior.
- `backend/src/main/java/com/ansh/bank/account/controller/AccountController.java` — account REST endpoints.
- `backend/src/main/java/com/ansh/bank/account/dto/AccountDtos.java` — account response DTOs.
- `backend/src/test/java/com/ansh/bank/AuthAccountApiTest.java` — auth/account API contract tests.
- `frontend/src/features/auth/LoginPage.tsx` — login screen using the real API.
- `frontend/src/features/dashboard/DashboardPage.tsx` — account summary dashboard using the real API.
- `frontend/src/features/auth/auth.test.tsx` — login component test.
- `e2e/auth-dashboard.spec.ts` — live login and dashboard journey.

### Transfer and beneficiary slice
- `backend/src/main/java/com/ansh/bank/transaction/document/BankTransaction.java` — transaction document.
- `backend/src/main/java/com/ansh/bank/transaction/repository/TransactionRepository.java` — transaction storage port.
- `backend/src/main/java/com/ansh/bank/transaction/service/TransactionService.java` — validated atomic simulated transfer behavior.
- `backend/src/main/java/com/ansh/bank/transaction/controller/TransactionController.java` — transfer/history REST endpoints.
- `backend/src/main/java/com/ansh/bank/transaction/dto/TransactionDtos.java` — transfer DTOs.
- `backend/src/main/java/com/ansh/bank/beneficiary/document/Beneficiary.java` — beneficiary document.
- `backend/src/main/java/com/ansh/bank/beneficiary/repository/BeneficiaryRepository.java` — beneficiary storage port.
- `backend/src/main/java/com/ansh/bank/beneficiary/service/BeneficiaryService.java` — beneficiary creation/list/verification behavior.
- `backend/src/main/java/com/ansh/bank/beneficiary/controller/BeneficiaryController.java` — beneficiary REST endpoints.
- `backend/src/main/java/com/ansh/bank/beneficiary/dto/BeneficiaryDtos.java` — beneficiary DTOs.
- `backend/src/test/java/com/ansh/bank/TransferBeneficiaryApiTest.java` — transfer and beneficiary API tests.
- `frontend/src/features/transfers/TransferPage.tsx` — transfer form and transaction history.
- `frontend/src/features/beneficiaries/BeneficiariesPage.tsx` — add/list/verify beneficiaries.
- `frontend/src/features/transfers/transfers.test.tsx` — transfer validation component test.
- `e2e/transfers-beneficiaries.spec.ts` — live beneficiary and transfer journey.

### Card, statement, and support slice
- `backend/src/main/java/com/ansh/bank/card/document/Card.java` — card domain document.
- `backend/src/main/java/com/ansh/bank/card/repository/CardRepository.java` — card storage port.
- `backend/src/main/java/com/ansh/bank/card/service/CardService.java` — card query/block/unblock/PIN behavior.
- `backend/src/main/java/com/ansh/bank/card/controller/CardController.java` — card REST endpoints.
- `backend/src/main/java/com/ansh/bank/card/dto/CardDtos.java` — card DTOs.
- `backend/src/main/java/com/ansh/bank/support/document/SupportTicket.java` — support ticket document.
- `backend/src/main/java/com/ansh/bank/support/repository/SupportTicketRepository.java` — ticket storage port.
- `backend/src/main/java/com/ansh/bank/support/service/SupportService.java` — ticket creation/list behavior.
- `backend/src/main/java/com/ansh/bank/support/controller/SupportController.java` — support REST endpoints.
- `backend/src/main/java/com/ansh/bank/support/dto/SupportDtos.java` — support DTOs.
- `backend/src/main/java/com/ansh/bank/statement/controller/StatementController.java` — CSV statement download endpoint.
- `backend/src/test/java/com/ansh/bank/CardSupportApiTest.java` — card/support/statement API tests.
- `frontend/src/features/cards/CardsPage.tsx` — cards list and block/unblock actions.
- `frontend/src/features/support/SupportPage.tsx` — support ticket form/list.
- `frontend/src/features/statements/StatementsPage.tsx` — statement download action.
- `frontend/src/features/cards/cards.test.tsx` — card action component test.
- `e2e/cards-support-statements.spec.ts` — live card, ticket, and statement journeys.

### Delivery
- `playwright.config.ts` — Playwright configuration and JSON evidence.
- `README.md` — setup, demo credentials, architecture, and test commands.
- `todos.yaml` — live per-feature implementation worklist.
- `test_report.yaml` — unit/API/E2E results and per-slice verification.
- `publish_manifest.yaml` — GitHub publishing record.
- `developer_metadata.json` — downstream implementation metadata.

## Ordered feature slices
1. **Auth and dashboard:** implement auth plus customer account retrieval end-to-end through `AuthController.java`, `AuthService.java`, user/account documents and repositories, `LoginPage.tsx`, `DashboardPage.tsx`, API/component tests, and `e2e/auth-dashboard.spec.ts`.
2. **Transfers and beneficiaries:** implement authenticated beneficiary management and transfer/history through transaction and beneficiary controller/service/document/DTO files, `TransferPage.tsx`, `BeneficiariesPage.tsx`, API/component tests, and `e2e/transfers-beneficiaries.spec.ts`.
3. **Cards, statements, and support:** implement card controls, statement CSV download, and support tickets through their controllers/services/documents/DTOs, feature pages, API/component tests, and `e2e/cards-support-statements.spec.ts`.

## Verification
- Backend unit/API: `mvn -f pom.xml test` → all JUnit API contract tests pass.
- Frontend unit: `node node_modules/vitest/vitest.mjs run` from `frontend` → all component tests pass.
- Build: `mvn -f pom.xml package -DskipTests` and `node node_modules/vite/bin/vite.js build` from `frontend` → both exit 0.
- E2E: after starting Spring on port 8080 and Vite on port 5173, `node node_modules/@playwright/test/cli.js test` → all live customer journeys pass and write JSON results.

## Scope note
The HLD lists notifications, audit, KYC administration, MFA, Redis, and Kafka as platform modules. Their production integration requires the unprovided infrastructure credentials and is not made fake in the runnable sandbox. The implemented representative customer modules retain the LLD's layering and complete the user-facing flows exercised by this build.
