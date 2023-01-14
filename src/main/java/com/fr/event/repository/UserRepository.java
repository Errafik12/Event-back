package com.fr.event.repository;

import com.fr.event.domain.User;

public interface UserRepository {

    int createUser(User user);

    User getUserByName(String name);

    int updateUserRole(String name, String role);

}
