package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helper.Helper;
import com.scm.scm.helper.Message;
import com.scm.scm.helper.MessageType;
import com.scm.scm.services.ContactService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactsController {

        @Autowired
        ContactService contactService;    

        @RequestMapping("/add")
        public String contactView(Model model) {
            ContactForm contactForm = new ContactForm();
            model.addAttribute("contactForm", contactForm);
            return "user/add_contact";
        }

        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public String saveContact( @Valid @ModelAttribute ContactForm contactForm,BindingResult bindingResult,HttpSession session,Authentication authentication ) {
            System.out.println("Processing Contact:");
            System.out.println(contactForm);

            if (bindingResult.hasErrors()) {
                return "user/add_contact";
            }
            String userName = Helper.getLoggedInUserEmail(authentication);
            contactService.saveContact(contactForm,userName);
            Message message =  Message.builder().textContent("Contact added successfully").type(MessageType.green).build();
            session.setAttribute("message",message);
            return "redirect:/user/contacts/add";
        }

}
