package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User implements UserDetails {

    @Id
    private String userId;
    @Column(name = "username", nullable = false)
    private String name;
    @Column(unique = true , nullable = false)
    private String email;
    @Getter(value = AccessLevel.NONE)
    private String password;
    @Column(length = 10000)
    private String about;
    @Column(length = 100000)
    private String profiePic;
    private String phoneNo;

    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    @Enumerated(value = EnumType.STRING)
    private Providers providers = Providers.SELF;
    private String providerUserId;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contactList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
