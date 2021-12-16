package com.jonathan.signinsignup.signup.controllers;

import com.jonathan.signinsignup.signup.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.signup.models.dtos.SignUpResponseDto;
import com.jonathan.signinsignup.signup.services.SignUpService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/sign-up")
    public SignUpResponseDto signUp(@Validated @RequestBody SignUpRequestDto signUpRequestDto) {
        return this.signUpService.signUp(signUpRequestDto);
    }
}
