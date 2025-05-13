package com.pirgeo.example.daqsample;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static io.opentelemetry.api.common.AttributeKey.stringKey;

@RestController
public class CoffeeshopController {
    private final LongCounter beverageCounter;

    private static final String BEVERAGE_TYPE = "beverage_type";
    private static final String CUSTOMER_EMAIL = "customer_email";

    private static final Random RANDOM = new Random();

    public CoffeeshopController(OpenTelemetry openTelemetry) {
        Meter meter = openTelemetry.getMeter(CoffeeshopController.class.getName());

        beverageCounter = meter.counterBuilder("coffeeshop.beverages_ordered").build();
    }

    @GetMapping("/order/cappuccino")
    public ResponseEntity<String> cappuccino() {
        beverageCounter.add(1, generateAttributes("Cappuccino"));

        return ResponseEntity.ok("Serving cappuccino");
    }

    @GetMapping("/order/espresso")
    private ResponseEntity<String> espresso() {
        beverageCounter.add(1, generateAttributes("Espresso"));

        return ResponseEntity.ok("Serving espresso");
    }

    @GetMapping("/order/hot-chocolate")
    private ResponseEntity<String> hotChocolate() {
        beverageCounter.add(1, generateAttributes("Hot Chocolate"));

        return ResponseEntity.ok("Serving hot chocolate");
    }

    private Attributes generateAttributes(String beverageType) {
        return Attributes.of(
                // this attribute is useful
                // we can determine how many of each type of beverage were consumed
                stringKey(BEVERAGE_TYPE), beverageType,
                // this could be considered personal information that should not be stored.
                // We will redact this in the data ingest pipeline
                stringKey(CUSTOMER_EMAIL), getRandomCustomerEmail()
        );
    }

    private static String getRandomCustomerEmail() {
        List<String> customerMailAddresses = List.of("danielthompson@example.de", "catherine.andrews@example.at", "frank.sanchez@fmail.co.uk", "taylor.hughes@example.at", "sophia.flores@somemail.at");
        return customerMailAddresses.get(RANDOM.nextInt(customerMailAddresses.size()));
    }
}
