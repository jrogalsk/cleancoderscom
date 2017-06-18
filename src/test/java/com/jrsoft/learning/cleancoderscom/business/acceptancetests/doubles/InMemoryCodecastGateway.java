package com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles;

import com.jrsoft.learning.cleancoderscom.business.Codecast;
import com.jrsoft.learning.cleancoderscom.business.CodecastGateway;

import java.util.List;

public class InMemoryCodecastGateway extends GatewayUtilities<Codecast> implements CodecastGateway {
    @Override
    public List<Codecast> findAllCodecastsSortedChronologically() {
        throw new UnsupportedOperationException("#findAllCodecastsSortedChronologically()");
    }

    @Override
    public Codecast findCodecastByTitle(String codecastTitle) {
        throw new UnsupportedOperationException("#findCodecastByTitle()");
    }
}
