package com.example.server.config;

import com.example.server.entity.Account;
import com.example.server.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyAccountDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Account> user = accountRepository.findByUserName(userName);
        if (user.isPresent()) {
            Account u = user.get();

            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(u.getUserName())
                    .password(u.getPassword())
                    .roles(u.getRole())
                    .build();
            return userDetails;
        }


        return null;
    }
}