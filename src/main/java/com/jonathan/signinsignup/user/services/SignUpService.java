package com.jonathan.signinsignup.user.services;

import com.jonathan.signinsignup.user.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.user.models.dtos.SignUpResponseDto;

public interface SignUpService {
    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);
}
