package com.scm.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.scm.entities.Contact;
import com.scm.scm.forms.ContactForm;


public interface ContactService {
    Contact saveContact(Contact contact);

    Contact saveContact(ContactForm contactForm,String userName);

    Contact getContact(String id);

    Contact updateContact(Contact contact);

    void deleteContact(String id);

    List<Contact> getAllContacts();

    Page<Contact> search(String username,String name, String email, String phone,int size, int page, String sort,String direction);

    List<Contact> getByUserName(String userName);

    Page<Contact> getByUser(String userName,int size, int page, String sort,String direction);
    
}
