package com.example.memderadmin.api;

import com.example.memderadmin.app.LoginRequest;
import com.example.memderadmin.app.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {

    @PostMapping("/intsvc/admin/homepage/v1/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(LoginResponse.of(null));
    }
}