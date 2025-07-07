package com.blakewashington.wealthcalc.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final String userId;
    private final String email;

    public JwtAuthentication(String userId, String email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userId = userId;
        this.email = email;
        setAuthenticated(true); // mark as authenticated
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }
}
