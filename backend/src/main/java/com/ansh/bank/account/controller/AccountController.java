package com.ansh.bank.account.controller;
import java.util.*; import com.ansh.bank.account.dto.AccountDtos.*; import com.ansh.bank.account.service.AccountService; import org.springframework.web.bind.annotation.*;
/** Expose account overview data. */ @RestController @RequestMapping("/api/accounts") public class AccountController { private final AccountService s; public AccountController(AccountService s){this.s=s;} /** Return all customer accounts. */ @GetMapping public List<AccountResponse> list(){return s.all();} }
