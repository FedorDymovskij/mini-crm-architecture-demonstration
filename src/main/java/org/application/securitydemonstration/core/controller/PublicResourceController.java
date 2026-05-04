package org.application.securitydemonstration.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/public")
@RestController
public class PublicResourceController {

    @GetMapping("/resource")
    public ResponseEntity<String> resource() {
        return ResponseEntity.ok("This is a public resource for any unauthenticated users");
    }
}
