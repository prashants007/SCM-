package com.scm.scm.repositories;
import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    Page<Contact> findByUser(User user, Pageable pageable);

    Page<Contact> findByUserAndNameContainingOrEmailContainingOrPhoneNoContaining(User user,String name, String email, String phoneNo, Pageable pageable);
}
