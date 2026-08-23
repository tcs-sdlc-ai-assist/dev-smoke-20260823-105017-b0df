package com.ansh.bank.card.dto;
/** Group card transport records. */ public final class CardDtos { private CardDtos(){} /** Describe a payment card. */ public record Response(String id,String name,String last4,String status){} }
