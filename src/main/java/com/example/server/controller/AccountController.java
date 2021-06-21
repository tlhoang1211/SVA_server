package com.example.server.controller;

import com.example.server.entity.Account;
import com.example.server.entity.Credential;
import com.example.server.model.dto.AccountDTO;
import com.example.server.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping()
    public AccountDTO create (@RequestBody Account account){
        return accountService.save(account);
    }

    @PostMapping("/login")
    public String login (@RequestParam String username,
                         @RequestParam String password) {
        Credential credential = accountService.login(username, password);
        return credential.getTokenKey();
    }

    @GetMapping("/get-user")
    public AccountDTO getUser(@RequestParam String token){
        return accountService.findAccountByToken(token);
    }

}

