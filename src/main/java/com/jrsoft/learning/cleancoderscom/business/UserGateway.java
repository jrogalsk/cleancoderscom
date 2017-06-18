package com.jrsoft.learning.cleancoderscom.business;

public interface UserGateway {
    User save(User user);

    User findUserByName(String username);

    User findUser(String username);
}
