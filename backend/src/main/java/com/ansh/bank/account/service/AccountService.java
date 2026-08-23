package com.ansh.bank.account.service;
import java.util.*; import com.ansh.bank.common.config.SeedData; import com.ansh.bank.account.dto.AccountDtos.*; import org.springframework.stereotype.Service;
/** Query the signed-in customer's accounts. */ @Service public class AccountService { private final SeedData d; public AccountService(SeedData d){this.d=d;} /** List available accounts. */ public List<AccountResponse> all(){return d.accounts.stream().map(a->new AccountResponse(a.id(),a.name(),a.number(),a.balance(),a.type())).toList();} }
