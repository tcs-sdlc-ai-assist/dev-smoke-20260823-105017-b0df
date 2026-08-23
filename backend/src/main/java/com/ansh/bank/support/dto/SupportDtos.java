package com.ansh.bank.support.dto;
import java.time.LocalDateTime; /** Group support transport records. */ public final class SupportDtos { private SupportDtos(){} /** Carry a customer question. */ public record CreateRequest(String subject,String message){} /** Describe a ticket. */ public record Response(String id,String subject,String message,String status,LocalDateTime createdAt){} }
