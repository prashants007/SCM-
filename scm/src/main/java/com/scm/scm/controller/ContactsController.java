package com.scm.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.scm.entities.Contact;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helper.AppConstants;
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
        
        private Logger logger = LoggerFactory.getLogger(ContactsController.class);

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
                Message message =  Message.builder().textContent("Please fill the form correctly").type(MessageType.red).build();
            session.setAttribute("message",message);
            bindingResult.getAllErrors().forEach(error -> logger.info(error.toString()));
                return "user/add_contact";
            }
            String userName = Helper.getLoggedInUserEmail(authentication);
            contactService.saveContact(contactForm,userName);
            Message message =  Message.builder().textContent("Contact added successfully").type(MessageType.green).build();
            session.setAttribute("message",message);
            return "redirect:/user/contacts/add";
        }

        @RequestMapping
        public String getAllContacts(
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE) int size,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @RequestParam(value = "searchKeyWord",required = false) String search,
            Model model, Authentication authentication){
            String userName = Helper.getLoggedInUserEmail(authentication);
            // List<Contact> contactList = contactService.getByUser(userName);
            if(search!=null && !search.isEmpty()){
                Page<Contact> contactList = contactService.search(userName,search, search, search, size, page, sort, direction);
                model.addAttribute("contacts", contactList);
                return "user/contacts";
            }
            Page<Contact> contactList = contactService.getByUser(userName,size,page,sort,direction);
            model.addAttribute("contacts", contactList);

            return "user/contacts";
        }

}
