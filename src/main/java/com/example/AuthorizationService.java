package com.example;

import org.springframework.stereotype.Service;

public interface AuthorizationService {
    boolean isAllowed(Account account);
}
