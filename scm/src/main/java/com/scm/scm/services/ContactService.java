package com.scm.scm.services;

import java.util.List;

import com.scm.scm.entities.Contact;
import com.scm.scm.forms.ContactForm;


public interface ContactService {
    Contact saveContact(Contact contact);

    Contact saveContact(ContactForm contactForm,String userName);

    Contact getContact(String id);

    Contact updateContact(Contact contact);

    void deleteContact(String id);

    List<Contact> getAllContacts();

    List<Contact> search(String name, String email, String phone);

    List<Contact> getByUserId(String userName);
}
