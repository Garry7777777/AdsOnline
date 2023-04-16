package com.skypro.adsonline.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.skypro.adsonline.dto.*;
import com.skypro.adsonline.service.AuthService;
import static com.skypro.adsonline.dto.Role.USER;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {

        if (authService.login(req.getUsername(), req.getPassword())) return ResponseEntity.ok().build();
        else  return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {

        Role role = req.getRole() == null ? USER : req.getRole();
        if (authService.register(req, role)) return ResponseEntity.ok().build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
