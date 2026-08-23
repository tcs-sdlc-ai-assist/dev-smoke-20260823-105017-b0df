package com.ansh.bank.auth.controller;
import com.ansh.bank.auth.dto.AuthDtos.*; import com.ansh.bank.auth.service.AuthService; import org.springframework.web.bind.annotation.*;
/** Expose customer session endpoints. */ @RestController @RequestMapping("/api/auth") public class AuthController { private final AuthService service; public AuthController(AuthService s){service=s;} /** Sign in the seeded customer. */ @PostMapping("/login") public LoginResponse login(@RequestBody LoginRequest request){return service.login(request);} }
