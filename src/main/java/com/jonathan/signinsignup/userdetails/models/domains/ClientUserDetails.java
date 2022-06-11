package com.jonathan.signinsignup.userdetails.models.domains;

import com.jonathan.signinsignup.user.models.entities.ClientUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class ClientUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;

    public ClientUserDetails() {}

    public ClientUserDetails(ClientUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.isActive();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
