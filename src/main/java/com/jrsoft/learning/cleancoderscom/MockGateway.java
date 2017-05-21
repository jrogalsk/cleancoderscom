package com.jrsoft.learning.cleancoderscom;

import java.util.ArrayList;
import java.util.List;

public class MockGateway implements Gateway {
    private List<Codecast> codecasts;
    private List<User> users;
    public MockGateway() {
        this.codecasts = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    @Override
    public List<Codecast> findAllCodecasts() {
        return codecasts;
    }

    @Override
    public void delete(Codecast codecast) {
        codecasts.remove(codecast);
    }

    @Override
    public void save(Codecast codecast) {
        codecasts.add(codecast);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User findUser(String username) {
        return users.stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }
}
