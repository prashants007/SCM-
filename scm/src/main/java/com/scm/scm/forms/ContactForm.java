package com.scm.scm.forms;

import org.springframework.web.multipart.MultipartFile;

import com.scm.scm.validator.ValidFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContactForm {
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        private String email;
        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 10, message = "Invalid phone number")
        private String phoneNo;
        private String address;
        private String descritption;
        private Boolean isFavorite;
        private String websiteLink;
        private String linkedinLink;
        @ValidFile
        private MultipartFile image;


}
