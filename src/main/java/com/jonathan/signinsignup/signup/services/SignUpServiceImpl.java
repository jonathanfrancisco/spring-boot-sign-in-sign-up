package com.jonathan.signinsignup.signup.services;

import com.jonathan.signinsignup.common.ApiErrorException;
import com.jonathan.signinsignup.signup.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.signup.models.dtos.SignUpResponseDto;
import com.jonathan.signinsignup.signup.models.entities.ClientUser;
import com.jonathan.signinsignup.signup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

        ClientUser existingUser = this.userRepository.findByEmail(signUpRequestDto.getEmail());

        if(existingUser != null) {
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address already used. Please use another email address.");
        }

        if(!signUpRequestDto.getPassword().equals(signUpRequestDto.getPasswordConfirmation())) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST, "Password Confirmation does not matched entered password.");
        }

        // salt and hash password

        ClientUser user = new ClientUser();
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName((signUpRequestDto.getLastName()));
        user.setPassword(signUpRequestDto.getPassword());
        user.setEmail(signUpRequestDto.getEmail());
        this.userRepository.save(user);

        return SignUpResponseDto
                .builder()
                .message("You have successfully signed up!")
                .build();
    }
}
