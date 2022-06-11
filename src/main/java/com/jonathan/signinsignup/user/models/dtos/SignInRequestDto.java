package com.jonathan.signinsignup.user.models.dtos;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignInRequestDto {
    @NotEmpty
    @NotNull(message = "Username is required")
    private String username;

    @NotEmpty
    @NotNull(message = "Password is required")
    private String password;
}
