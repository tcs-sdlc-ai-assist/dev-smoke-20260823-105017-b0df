package com.ansh.bank.beneficiary.dto;
/** Group beneficiary request and response shapes. */ public final class BeneficiaryDtos { private BeneficiaryDtos(){} /** Carry a new payee. */ public record CreateRequest(String name,String accountNumber,String bank){} /** Describe a payee. */ public record Response(String id,String name,String accountNumber,String bank,String status){} }
