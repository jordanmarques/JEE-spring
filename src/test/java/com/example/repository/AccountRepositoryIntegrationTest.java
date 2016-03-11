package com.example.repository;

import com.example.account.J2ETestsApplication;
import com.example.account.model.Account;
import com.example.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J2ETestsApplication.class)

@Sql(statements = {"insert into Account (uuid, balance) values ('abc-123', '30')",
                    "insert into Account (uuid, balance) values ('abc-456', '80')",
                    "insert into Account (uuid, balance) values ('abc-789', '130')"},
     executionPhase = BEFORE_TEST_METHOD)

@Sql(statements = {"delete from Account"}, executionPhase = AFTER_TEST_METHOD)

public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void should_find_all(){
        assertThat(accountRepository.findAll(), hasSize(3));
    }

    @Test
    public void should_find_one_account_by_balance(){
        List<Account> byBalance = accountRepository.findByBalance(30);

        assertThat(byBalance, hasSize(1));
        assertThat(byBalance.get(0).getUuid(), is("abc-123"));

    }

    @Test
    public void should_find_with_pagination(){
        PageRequest pageRequest = new PageRequest(0, 2);

        Page<Account> firstPage = accountRepository.findAllBy(pageRequest);
        Page<Account> secondPage = accountRepository.findAllBy(firstPage.nextPageable());

        assertThat(firstPage.getTotalElements(), is(3L));
        assertThat(firstPage.getContent(), hasSize(2));
        assertThat(secondPage.getContent(), hasSize(1));

    }
}
