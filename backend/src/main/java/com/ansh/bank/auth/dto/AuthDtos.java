package com.ansh.bank.auth.dto;
/** Group authentication transport records. */ public final class AuthDtos { private AuthDtos(){} /** Carry demo credentials. */ public record LoginRequest(String email,String password){} /** Return access information. */ public record LoginResponse(String token,String name){} }
