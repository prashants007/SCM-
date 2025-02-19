package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.scm.entities.Contact;
import com.scm.scm.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired 
    ContactService contactService;

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable(name = "id") String id) {
        return contactService.getContact(id);
    }
   

}
