package com.example.server.config;

import com.example.server.entity.Account;
import com.example.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private AccountService accountService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object credential = usernamePasswordAuthenticationToken.getCredentials();
        if (credential == null) {
            throw new UsernameNotFoundException("Credential not found!");
        }

        String accessToken = String.valueOf(credential);

        Account account = accountService.findUserByToken(accessToken);
        if (account == null) {
            throw new UsernameNotFoundException("Cannot find user with authentication token:" + accessToken);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getUserName())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
}
