package org.example.concurrencysynchronizedjpa.controller;

import lombok.RequiredArgsConstructor;
import org.example.concurrencysynchronizedjpa.service.ApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/apply")
    public ResponseEntity<String> apply() {
        applyService.apply();
        return ResponseEntity.ok("OK");
    }
}
