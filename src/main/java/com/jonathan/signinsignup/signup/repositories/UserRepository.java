package com.jonathan.signinsignup.signup.repositories;

import com.jonathan.signinsignup.signup.models.entities.ClientUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<ClientUser, Long> {
    List<ClientUser> findAllByLastName(String lastName);
    ClientUser findById(long id);
    ClientUser findByEmail(String email);
}
