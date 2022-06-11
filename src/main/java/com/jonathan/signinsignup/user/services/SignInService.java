package com.jonathan.signinsignup.user.services;

import com.jonathan.signinsignup.user.models.dtos.SignInRequestDto;

public interface SignInService {
    String signIn(SignInRequestDto signInRequestDto);
}
