package com.example;

import com.example.account.exception.BlockedAccountException;
import com.example.account.exception.CreditNotAuthorizedException;
import com.example.account.model.Account;
import com.example.account.service.AccountService;
import com.example.account.service.AuthorizationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {



    @InjectMocks
    AccountService accountService;

    @Mock
    Account mockAccount;

    @Mock
    private AuthorizationService authorizationService;

    @Captor
    ArgumentCaptor<Integer> amountCaptor;

    @Before
    public void setup(){
        when( authorizationService.isAllowed(any())).thenReturn(true);
    }

    @Test
    public void should_add_money_nominal(){
        when(mockAccount.getBalance()).thenReturn(10);

        accountService.updateMoney(mockAccount, 30);

        verify(mockAccount).setBalance(amountCaptor.capture());
        assertThat(amountCaptor.getValue(), is(40));
    }

    @Test
    public void should_remove_money_nominal(){
        Account account = new Account(100);

        accountService.updateMoney(account, -30);

        assertThat(account.getBalance(), is(70));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_account_is_null(){
        accountService.updateMoney(null, 10);
    }

    @Test(expected = CreditNotAuthorizedException.class)
    public void should_throw_credit_exception_when_account_goes_under_zero(){
        Account account = Account.builder()
                .balance(10)
                .build();

        accountService.updateMoney(account, -30);
    }

    @Test(expected = BlockedAccountException.class)
    public void should_throw_blocked_account_Exception(){
        Account account = Account.builder()
                            .balance(1)
                            .build();

        when(authorizationService.isAllowed(any())).thenReturn(false);

        accountService.updateMoney(account, 10);
    }
}
