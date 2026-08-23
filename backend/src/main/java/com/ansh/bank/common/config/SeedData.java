package com.ansh.bank.common.config;

import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*; import org.springframework.stereotype.Component;
/** Hold deterministic development data in memory for the customer journey. */
@Component public class SeedData {
  /** Represent an owned account. */ public record Account(String id,String name,String number,BigDecimal balance,String type) {}
  /** Represent a payee. */ public record Beneficiary(String id,String name,String accountNumber,String bank,String status) {}
  /** Represent an account movement. */ public record Tx(String id,String accountId,String description,BigDecimal amount,String direction,LocalDateTime date) {}
  /** Represent a payment card. */ public record Card(String id,String name,String last4,String status) {}
  /** Represent a support request. */ public record Ticket(String id,String subject,String message,String status,LocalDateTime createdAt) {}
  public final List<Account> accounts=new ArrayList<>(List.of(new Account("acc-1","Everyday account","•••• 4821",new BigDecimal("8420.75"),"Current"),new Account("acc-2","Rainy-day savings","•••• 1290",new BigDecimal("15400.00"),"Savings")));
  public final List<Beneficiary> beneficiaries=new ArrayList<>(List.of(new Beneficiary("ben-1","Maya Patel","•••• 7744","Northstar Bank","VERIFIED")));
  public final List<Tx> transactions=new ArrayList<>(List.of(new Tx("tx-1","acc-1","Grocery Market",new BigDecimal("82.40"),"DEBIT",LocalDateTime.now().minusDays(2)),new Tx("tx-2","acc-1","Salary credit",new BigDecimal("4200.00"),"CREDIT",LocalDateTime.now().minusDays(6))));
  public final List<Card> cards=new ArrayList<>(List.of(new Card("card-1","Visa Signature","4821","ACTIVE")));
  public final List<Ticket> tickets=new ArrayList<>();
  /** Return the fixed demo password check. */ public boolean validUser(String email,String password){return "demo@ansh.bank".equals(email)&&"demo123".equals(password);}
}
