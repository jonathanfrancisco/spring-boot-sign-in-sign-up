package com.jonathan.signinsignup.user.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponseDto {
    private String jwt;
}
