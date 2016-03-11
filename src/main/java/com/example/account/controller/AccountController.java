package com.example.account.controller;


import com.example.account.exception.AccountEmptyException;
import com.example.account.exception.ValidationException;
import com.example.account.model.Account;
import com.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/accounts")
public class AccountController {


    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = GET)
    public List<Account> getAllAccounts(){

        return accountService.getAccounts();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account registerAccount(@RequestBody Account account){
        if(account.getBalance() == 0)
            throw new AccountEmptyException("balance account should not be 0 or less");

        return accountService.addAccount(account);
    }
}
