package com.jonathan.signinsignup.user.services;

import com.jonathan.signinsignup.common.errors.ApiErrorException;
import com.jonathan.signinsignup.user.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.user.models.dtos.SignUpResponseDto;
import com.jonathan.signinsignup.user.models.entities.ClientUser;
import com.jonathan.signinsignup.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        ClientUser existingUser = this.userRepository.findByUsername(signUpRequestDto.getUsername());

        if(existingUser != null && existingUser.getUsername().equals(signUpRequestDto.getUsername())) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already used. Please use another username.");
        }

        if(existingUser != null && existingUser.getUsername().equals(signUpRequestDto.getEmail())) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address already used. Please use another email address.");
        }

        if(!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordConfirmation())) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Password Confirmation does not matched entered password.");
        }

        ClientUser user = new ClientUser();
        user.setUsername(signUpRequestDto.getUsername());
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName((signUpRequestDto.getLastName()));
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setEmail(signUpRequestDto.getEmail());

        this.userRepository.save(user);

        return SignUpResponseDto
                .builder()
                .message("You have successfully signed up!")
                .build();
    }
}
