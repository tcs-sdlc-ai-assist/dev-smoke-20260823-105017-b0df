package com.ansh.bank.common.exception;
import java.util.Map; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
/** Convert expected business errors into a consistent JSON envelope. */ @RestControllerAdvice public class GlobalExceptionHandler { /** Map validation failures to bad requests. */ @ExceptionHandler(ApiException.class) public ResponseEntity<Map<String,String>> handle(ApiException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));} }
