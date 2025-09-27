# Latam Bank Microservices - Phase 1 (Simple)

## Services & Ports
- customer-service : 8081
- account-service  : 8082
- transfer-service : 8083
- deposit-service  : 8084
- forex-service    : 8085
- loi-service      : 8086
- payments-service : 8087

## Quick Start
Create MySQL schemas: customerdb, accountdb, depositdb, forexdb, loidb, paymentsdb.
Update `spring.datasource.*` if needed.

Run each service:
```bash
mvn spring-boot:run
```

## Core Endpoints

### Customer Management
- POST /customers
- GET  /customers
- GET  /customers/{id}
- PUT  /customers/{id}
- DELETE /customers/{id}

### Accounts Opening & Ops
- POST /accounts          (open)
- GET  /accounts
- GET  /accounts/{id}
- GET  /accounts/byCustomer/{customerId}
- POST /accounts/{id}/debit?amount=100
- POST /accounts/{id}/credit?amount=100

### Fixed & Recurring Deposits
- POST /deposits   (payload has depositType=FIXED|RECURRING, rate, tenure, etc.)
- GET  /deposits
- GET  /deposits/{id}
- GET  /deposits/{id}/maturity

### Funds Transfer
- POST /transfer?fromAcc=1&toAcc=2&amount=50

### Forex
- POST /forex/rate   (create/update rate)
- GET  /forex/rates
- GET  /forex/convert?from=USD&to=INR&amount=10

### Letter of Intent (LOI)
- POST /loi
- GET  /loi
- GET  /loi/{id}
- POST /loi/{id}/submit
- POST /loi/{id}/approve
- POST /loi/{id}/reject

### Bulk/Invoice Payments
- POST /payments/batch
  - Body: InvoiceBatch with `invoices` array
- GET  /payments/batches
- POST /payments/batch/{id}/process
