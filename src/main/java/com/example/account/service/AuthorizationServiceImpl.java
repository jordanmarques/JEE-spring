package com.example.account.service;

import com.example.account.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public boolean isAllowed(Account account) {
        return false;
    }
}
