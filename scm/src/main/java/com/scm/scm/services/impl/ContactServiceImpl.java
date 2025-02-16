package com.scm.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.repositories.ContactRepo;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.UserService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    UserService userService;
    @Override
    public Contact saveContact(Contact contact) {
        contact.setId(UUID.randomUUID().toString());
        contactRepo.save(contact);
        return contact;
    }

    @Override
    public Contact getContact(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    }

    @Override
    public Contact updateContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateContact'");
    }

    @Override
    public void deleteContact(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContact'");
    }

    @Override
    public List<Contact> getAllContacts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllContacts'");
    }

    @Override
    public List<Contact> search(String name, String email, String phone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getByUserId(String userName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByUserId'");
    }

    @Override
    public Contact saveContact(ContactForm contactForm,String userName) {
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNo(contactForm.getPhoneNo());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescritption());
        contact.setFavorite(contactForm.getIsFavorite());
        contact.setWebSiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedinLink()); 
        contact.setUser(userService.getUserByEmail(userName));    
        return saveContact(contact);
    }

}
