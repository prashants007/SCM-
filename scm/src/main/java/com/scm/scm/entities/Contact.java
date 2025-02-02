package com.scm.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contact {

    @Id
    private String id;
    private String name;
    private String phoneNo;
    private String address;
    private String picture;
    @Column(length = 10000)
    private String description;
    private boolean isFavorite = false;
    private String webSiteLink;
    private String linkedInLink;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact", fetch = FetchType.EAGER)
    private List<SocialLink> links = new ArrayList<>();
}
