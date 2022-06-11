package com.jonathan.signinsignup.user.repositories;

import com.jonathan.signinsignup.user.models.entities.ClientUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ClientUser, Long> {
    ClientUser findByUsername(String username);
}
