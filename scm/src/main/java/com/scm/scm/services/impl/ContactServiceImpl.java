package com.scm.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helper.ResourceNotFoundException;
import com.scm.scm.repositories.ContactRepo;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.ImgService;
import com.scm.scm.services.UserService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    UserService userService;

    @Autowired
    ImgService imgService;

    @Override
    public Contact saveContact(Contact contact) {
        contact.setId(UUID.randomUUID().toString());
        contactRepo.save(contact);
        return contact;
    }

    @Override
    public Contact getContact(String id) {
      return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found"));
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
    public List<Contact> getByUserName(String userName) {
        User user = userService.getUserByEmail(userName);
        return user.getContactList();
    }
    @Override
    public Page<Contact> getByUser(String userName,int size, int page, String sortBy,String direction) {
        User user = userService.getUserByEmail(userName);
        Sort sort = "desc".equals(direction)? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageRequest =  PageRequest.of(page, size, sort); 
                        
        return contactRepo.findByUser(user, pageRequest);
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
        String imgFileUrl = imgService.uploadImage(contactForm.getImage());
        contact.setPicture(imgFileUrl);
        return saveContact(contact);
    }

    @Override
    public Page<Contact> search(String username,String name, String email, String phone, int size, int page, String sort,
            String direction) {
                User user = userService.getUserByEmail(username);
                Sort sortObj = "desc".equals(direction)? Sort.by(sort).descending() : Sort.by(sort).ascending();
                Pageable pageable = PageRequest.of(page, size, sortObj);
                return contactRepo.findByUserAndNameContainingOrEmailContainingOrPhoneNoContaining(user,name, email, phone, pageable);
    }

}
