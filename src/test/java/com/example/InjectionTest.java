package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = J2ETestsApplication.class)
public class InjectionTest {

    @Autowired
    ApplicationContext context;


    @Test
    public void discovery(){
        assertThat(context, notNullValue());

        AccountService accountServiceBean
                = context.getBean(AccountService.class);

        assertThat(
                accountServiceBean,
                notNullValue());

        AuthorizationService authorizationServiceBean
                = context.getBean(AuthorizationService.class);

        assertThat(
                authorizationServiceBean,
                is(accountServiceBean.getAuthorizationService()));
    }
}
