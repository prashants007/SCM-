package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "user")
@Table(name = "users")
public class User {

    @Id
    private String userId;
    @Column(name = "username", nullable = false)
    private String name;
    @Column(unique = true , nullable = false)
    private String email;
    private String password;
    @Column(length = 10000)
    private String about;
    @Column(length = 100000)
    private String profiePic;
    private String phoneNo;

    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    private Providers providers = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contactList = new ArrayList<>();
}
