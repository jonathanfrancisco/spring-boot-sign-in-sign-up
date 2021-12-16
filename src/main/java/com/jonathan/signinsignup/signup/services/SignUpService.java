package com.jonathan.signinsignup.signup.services;

import com.jonathan.signinsignup.signup.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.signup.models.dtos.SignUpResponseDto;

public interface SignUpService {
    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);
}
