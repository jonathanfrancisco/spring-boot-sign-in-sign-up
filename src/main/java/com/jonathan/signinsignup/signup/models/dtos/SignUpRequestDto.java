package com.jonathan.signinsignup.signup.models.dtos;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class SignUpRequestDto {

    @NotEmpty
    @NotNull(message = "First name is required")
    private String firstName;

    @NotEmpty
    @NotNull(message = "Last name is required")
    private String lastName;

    @NotEmpty
    @NotNull(message = "Username is required")
    private String username;

    @NotEmpty
    @NotNull(message = "Password is required")
    private String password;

    @NotEmpty
    @NotNull(message = "Password Confirmation is required")
    private String passwordConfirmation;

    @NotEmpty
    @NotNull(message = "Email is required")
    @Email
    private String email;
}
