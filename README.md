# spring-cloud-stream Demo

Demo-Anwendung für Spring Cloud Stream. Der Message-Broker ist austauschbar:
RabbitMQ und Kafka liegen beide auf dem Klassenpfad und werden über zwei
Spring-Profile ausgewählt – der Anwendungscode (Producer/Consumer) bleibt
dabei unverändert.

## Aufbau

- `OrderEvent` – einfaches Domänenobjekt (`orderId`, `product`, `quantity`, `timestamp`)
- `OrderController` – `POST /orders`, sendet ein `OrderEvent` per `StreamBridge` auf das Binding `orders-out-0`
- `OrderEventHandler` – `Consumer<OrderEvent>` Bean, konsumiert das Binding `orderEventHandler-in-0` und loggt das Event
- Beide Bindings zeigen auf die logische Destination `orders`; das Ziel-Topic/die Queue wird vom jeweiligen Binder erzeugt

Die Profile `rabbit` und `kafka` (`application-rabbit.yaml` / `application-kafka.yaml`)
setzen jeweils nur `spring.cloud.stream.default-binder` sowie die
Broker-Verbindungsdaten.

## Voraussetzungen

- Java 26
- Docker (für RabbitMQ bzw. Kafka)

## RabbitMQ-Variante

```bash
docker compose --profile rabbit up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=rabbit
```

RabbitMQ-Management-UI: http://localhost:15672 (guest/guest)

## Kafka-Variante

```bash
docker compose --profile kafka up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=kafka
```

## Demo ausprobieren

```bash
curl -X POST localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"orderId":"order-1","product":"widget","quantity":3,"timestamp":"2026-08-15T12:00:00Z"}'
```

Im Log der Anwendung erscheint anschliessend `Received order event: ...` –
unabhängig davon, ob RabbitMQ oder Kafka als Binder aktiv ist.

Aktuelle Binder-Status: http://localhost:8080/actuator/bindings

## Tests

```bash
./mvnw test
```

Die Tests laufen ohne echten Broker gegen den
`spring-cloud-stream-test-binder` (`spring.cloud.stream.default-binder=test`).
