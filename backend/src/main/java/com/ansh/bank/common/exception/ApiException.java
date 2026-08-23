package com.ansh.bank.common.exception;
/** Signal a client-visible business rule failure. */ public class ApiException extends RuntimeException { /** Create an error with message. */ public ApiException(String message){super(message);} }
