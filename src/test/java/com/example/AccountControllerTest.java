package com.example;

import com.example.account.controller.AccountController;
import com.example.account.exception.AccountEmptyException;
import com.example.account.exception.ValidationException;
import com.example.account.model.Account;
import com.example.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    @Mock
    Account account;

    @Test
    public void should_get_account_from_account_service(){
        accountController.getAllAccounts();

        verify(accountService).getAccounts();
    }

    @Test
    public void should_create_new_account(){
        when(account.getBalance()).thenReturn(1);

        accountController.registerAccount(account);

        verify(accountService).addAccount(account);
    }

    @Test(expected = AccountEmptyException.class)
    public void should_not_create_new_account_when_account_is_empty(){
        when(account.getBalance()).thenReturn(0);

        accountController.registerAccount(account);

    }
}
