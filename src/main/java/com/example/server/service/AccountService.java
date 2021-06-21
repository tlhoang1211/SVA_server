package com.example.server.service;

import com.example.server.entity.Account;
import com.example.server.entity.Credential;
import com.example.server.model.dto.AccountDTO;
import com.example.server.model.mapper.AccountMapper;
import com.example.server.repository.CredentialRepository;
import com.example.server.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountDTO save (Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        AccountDTO rs = AccountMapper.accountDTO(account);
        return rs;
    }

    public AccountDTO register (String username, String password, int role) {
        Account account = new Account(username, bCryptPasswordEncoder.encode(password), role);
        accountRepository.save(account);
        return null;
    }

    public Credential login(String username, String password) {
        Optional<Account> userOptional = accountRepository.findByUserName(username);
        if (userOptional.isPresent()) {
            Account account = userOptional.get();
            if (bCryptPasswordEncoder.matches(password, account.getPassword())) {
                Credential credential = new Credential(account);
                return credentialRepository.save(credential);
            }
        }
        return null;
    }

    public Account findUserByToken(String accessToken){
        Optional<Credential> credentialOptional = credentialRepository.findById(accessToken);
        if (credentialOptional.isPresent()) {
            Credential credential = credentialOptional.get();
            if(credential.isExpired()){
                return null;
            }

            return credential.getAccount();
        }

        return null;
    }

    public AccountDTO findAccountByToken(String accessToken){
        Optional<Credential> credentialOptional = credentialRepository.findById(accessToken);
        if (credentialOptional.isPresent()) {
            Credential credential = credentialOptional.get();
            if(credential.isExpired()){
                return null;
            }

            Account account = credential.getAccount();

            AccountDTO rs = AccountMapper.accountDTO(account);
            return rs;
        }

        return null;
    }
}

