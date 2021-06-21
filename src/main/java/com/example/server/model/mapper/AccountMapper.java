package com.example.server.model.mapper;

import com.example.server.entity.Account;
import com.example.server.model.dto.AccountDTO;

public class AccountMapper {
    public static AccountDTO accountDTO (Account account) {
        AccountDTO tmp = new AccountDTO();
        tmp.setUserName(account.getUserName());
        tmp.setPassword(account.getPassword());
        return tmp;
    }
}
