package com.example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;


@Setter
@Getter
@Service
public class AccountService {

    private AuthorizationService authorizationService;
    private List<Account> accounts;

    @Autowired
    public AccountService(AuthorizationService authorizedService) {
        this.authorizationService = authorizedService;
        this.accounts = Arrays.asList(10, 20, 50).stream()
                .map(Account::new)
                .collect(Collectors.toList());
    }

    public void updateMoney(Account account, int i) throws CreditNotAuthorizedException {
        notNull(account, "Account must not be null");

        if(account.getBalance() + i < 0)
            throw new CreditNotAuthorizedException();

        if(!authorizationService.isAllowed(account))
            throw new BlockedAccountException();

        account.setBalance(account.getBalance() + i);
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public Account addAccount(Account account) {
        return this.accounts.add(account) ? account : null;
    }
}
