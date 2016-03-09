package com.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class Account {

    private Integer balance;

    public Account() {
    }

    public Account(int balance) {
        this.balance = balance;
    }

    public Account(Integer balance) {
        this.balance = balance;
    }
}
