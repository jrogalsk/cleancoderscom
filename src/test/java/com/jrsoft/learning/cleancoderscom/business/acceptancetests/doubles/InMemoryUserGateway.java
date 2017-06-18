package com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles;

import com.jrsoft.learning.cleancoderscom.business.User;
import com.jrsoft.learning.cleancoderscom.business.UserGateway;

public class InMemoryUserGateway extends GatewayUtilities<User> implements UserGateway {

    @Override
    public User findUserByName(String username) {
        for (User user : getEntities()) {
            if (user.getUserName().equals(username))
                return user;
        }
        return null;
    }

    @Override
    public User findUser(String username) {
        throw new UnsupportedOperationException("#findUser()");
    }
}
