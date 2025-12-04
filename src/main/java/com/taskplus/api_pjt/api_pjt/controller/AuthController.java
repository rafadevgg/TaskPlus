package com.taskplus.api_pjt.api_pjt.controller;

import com.taskplus.api_pjt.api_pjt.dto.request.UsuarioLoginDto;
import com.taskplus.api_pjt.api_pjt.dto.request.UsuarioRegisterDto;
import com.taskplus.api_pjt.api_pjt.dto.response.AuthResponseDto;
import com.taskplus.api_pjt.api_pjt.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registro(@RequestBody @Valid UsuarioRegisterDto dto) {
        AuthResponseDto response = authService.registro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid UsuarioLoginDto dto) {
        AuthResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}