package com.jonathan.signinsignup.signup.services;

import com.jonathan.signinsignup.signup.models.dtos.SignUpRequestDto;
import com.jonathan.signinsignup.signup.models.dtos.SignUpResponseDto;
import com.jonathan.signinsignup.signup.models.entities.ClientUser;
import com.jonathan.signinsignup.signup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new Error("User already exists!");
        }

        ClientUser user = new ClientUser();
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName((signUpRequestDto.getLastName()));
        user.setPassword(signUpRequestDto.getPassword());
        user.setEmail(signUpRequestDto.getEmail());

        this.userRepository.save(user);


        // validate fields e.g username already exists

        // salt and hash password

        // save to database via JPA repository (Hibernate)

        // return nicely formatted response

        return SignUpResponseDto
                .builder()
                .message("You have successfully signed up!")
                .build();
    }
}
