package com.pirgeo.example.daqsample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeshopController {
    public CoffeeshopController() {
    }

    @GetMapping("/order/cappuccino")
    public ResponseEntity<String> cappuccino() {
        return ResponseEntity.ok("Serving cappuccino");
    }

    @GetMapping("/order/espresso")
    private ResponseEntity<String> espresso() {
        return ResponseEntity.ok("Serving espresso");
    }

    @GetMapping("/order/hot-chocolate")
    private ResponseEntity<String> hotChocolate() {
        return ResponseEntity.ok("Serving hot chocolate");
    }
}
