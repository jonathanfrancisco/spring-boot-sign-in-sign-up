package com.jonathan.signinsignup.user.services;

import com.jonathan.signinsignup.common.errors.ApiErrorException;
import com.jonathan.signinsignup.user.models.dtos.SignInRequestDto;
import com.jonathan.signinsignup.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public String signIn(SignInRequestDto signInRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequestDto.getUsername(),
                    signInRequestDto.getPassword()
            ));
        } catch(BadCredentialsException e) {
            throw new ApiErrorException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                signInRequestDto.getUsername()
        );

        return jwtUtil.generateToken(userDetails);
    }
}
