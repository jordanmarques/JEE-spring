package com.example;


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

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = GET)
    public List<Account> getAllAccounts(){

        return accountService.getAccounts();

    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccounts(@RequestBody Account account){
        return accountService.addAccount(account);
    }
}
