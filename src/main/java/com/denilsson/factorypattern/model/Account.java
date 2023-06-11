package com.denilsson.factorypattern.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {

    private String id;
    private String name;
    private String lastName;
    private String accountType;
    private Double balance;
}
