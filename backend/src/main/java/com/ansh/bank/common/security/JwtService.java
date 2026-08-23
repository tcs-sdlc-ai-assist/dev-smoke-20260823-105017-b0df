package com.ansh.bank.common.security;
import org.springframework.stereotype.Service;
/** Create and validate a deliberately simple development access token. */
@Service public class JwtService { /** Issue a token for the supplied user. */ public String issue(String email){return "ansh-demo-"+email;} /** Validate a token. */ public boolean valid(String token){return token!=null&&token.startsWith("ansh-demo-");} }
