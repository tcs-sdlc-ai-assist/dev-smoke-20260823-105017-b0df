package com.ansh.bank.account.dto;
import java.math.BigDecimal; /** Group account responses. */ public final class AccountDtos { private AccountDtos(){} /** Describe one customer account. */ public record AccountResponse(String id,String name,String number,BigDecimal balance,String type){} }
