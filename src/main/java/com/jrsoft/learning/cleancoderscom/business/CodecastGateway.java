package com.jrsoft.learning.cleancoderscom.business;

import java.util.List;

public interface CodecastGateway {
    List<Codecast> findAllCodecastsSortedChronologically();

    Codecast save(Codecast codecast);

    void delete(Codecast codecast);

    Codecast findCodecastByTitle(String codecastTitle);
}
