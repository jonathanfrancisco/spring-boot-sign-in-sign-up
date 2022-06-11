package com.jonathan.signinsignup.user.controllers;

import com.jonathan.signinsignup.user.models.dtos.SignInRequestDto;
import com.jonathan.signinsignup.user.models.dtos.SignInResponseDto;
import com.jonathan.signinsignup.user.services.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignInController {

    private final SignInService signInService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequestDto signInRequestDto) {
        String jwt = signInService.signIn(signInRequestDto);

        return ResponseEntity.ok(
                SignInResponseDto
                .builder().jwt(jwt)
                .build()
        );
    }

    @GetMapping("/protected-route")
    public String protectedRoute(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return String.format("You have accessed the protected route! %s",userDetails.getUsername());
    }


}
