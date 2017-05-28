package com.jrsoft.learning.cleancoderscom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class MockGateway implements Gateway {
    private List<Codecast> codecasts;
    private List<User> users;
    private List<License> licenses;

    public MockGateway() {
        codecasts = new ArrayList<>();
        users = new ArrayList<>();
        licenses = new ArrayList<>();
    }

    @Override
    public List<Codecast> findAllCodecastsSortedChronologically() {
        List<Codecast> sortedCodecasts = new ArrayList<Codecast>(codecasts);
        Collections.sort(sortedCodecasts, new Comparator<Codecast>() {
            public int compare(Codecast o1, Codecast o2) {
                return o1.getPublicationDate().compareTo(o2.getPublicationDate());
            }
        });
        return sortedCodecasts;
    }

    @Override
    public void delete(Codecast codecast) {
        codecasts.remove(codecast);
    }

    @Override
    public Codecast save(Codecast codecast) {
        codecasts.add((Codecast) establishId(codecast));
        return codecast;
    }

    @Override
    public User save(User user) {
        users.add((User) establishId(user));
        return user;
    }

    private Entity establishId(Entity user) {
        if (isNull(user.getId()))
            user.setId(UUID.randomUUID().toString());
        return user;
    }

    @Override
    public User findUser(String username) {
        return users.stream()
                .filter(user -> user.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Codecast findCodecastByTitle(String codecastTitle) {
        return codecasts.stream()
                .filter(codecast -> codecast.getTitle().equals(codecastTitle))
                .findFirst()
                .orElse(null);

    }

    @Override
    public void save(License license) {
        licenses.add(license);
    }

    @Override
    public List<License> findLicensesForUserAndCodecast(User user, Codecast codecast) {
        return licenses.stream()
                .filter(license -> license.getUser().isSame(user))
                .filter(license -> license.getCodecast().isSame(codecast))
                .collect(Collectors.toList());
    }
}
