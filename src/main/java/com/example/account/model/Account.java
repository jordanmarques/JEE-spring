package com.example.account.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Account {

    @Id
    private String uuid;

    @Column
    private Integer balance;

    public Account(int balance) {
        this.balance = balance;
    }
}
