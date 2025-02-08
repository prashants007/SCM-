package com.scm.scm.forms;

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
public class UserForm {

    @NotBlank(message = "User Name is Required")
    @Size(min = 3, message = "minimum 3 characters are required")
    private String name;
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is Required")
    private String email;
    @NotBlank(message = "Password is Required")
    @Size(min = 6, message = "Min length should be 6")
    private String password;
    @NotBlank(message = "About is Required")
    private String about;
    @Size(min = 8, max = 12,message = "Invalid Phone Number")
    private String contactNo;
}
