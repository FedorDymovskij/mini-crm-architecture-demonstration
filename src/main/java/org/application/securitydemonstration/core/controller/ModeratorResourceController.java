package org.application.securitydemonstration.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity
@RequestMapping("/moderator")
@RestController
public class ModeratorResourceController {

    @GetMapping("/resource")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> resource() {
        return ResponseEntity.ok("This is protected resource for any authenticated users with role 'MODERATOR'");
    }
}
