package com.groupnine.macramicia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
