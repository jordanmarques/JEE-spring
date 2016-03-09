package com.example;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public boolean isAllowed(Account account) {
        return false;
    }
}
