package com.jonathan.signinsignup.user.controllers;

import com.jonathan.signinsignup.user.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.user.models.dtos.SignUpResponseDto;
import com.jonathan.signinsignup.user.services.SignUpService;

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
