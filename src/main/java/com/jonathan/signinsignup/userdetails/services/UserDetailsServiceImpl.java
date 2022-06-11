package com.jonathan.signinsignup.userdetails.services;

import com.jonathan.signinsignup.user.models.entities.ClientUser;
import com.jonathan.signinsignup.user.repositories.UserRepository;
import com.jonathan.signinsignup.userdetails.models.domains.ClientUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientUser user = userRepository.findByUsername(username);
        return new ClientUserDetails(user);
    }
}
