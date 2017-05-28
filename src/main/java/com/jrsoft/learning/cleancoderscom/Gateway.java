package com.jrsoft.learning.cleancoderscom;

import java.util.List;

public interface Gateway {
    List<Codecast> findAllCodecasts();

    void delete(Codecast codecast);

    Codecast save(Codecast codecast);

    User save(User user);

    User findUser(String username);

    Codecast findCodecastByTitle(String codecastTitle);

    void save(License license);

    List<License> findLicensesForUserAndCodecast(User user, Codecast codecast);

}
