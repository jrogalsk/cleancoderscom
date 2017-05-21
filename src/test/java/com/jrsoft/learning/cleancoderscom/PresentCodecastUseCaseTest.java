package com.jrsoft.learning.cleancoderscom;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PresentCodecastUseCaseTest {

    private User user;
    private Codecast codecast;
    private License viewLicense;

    @Before
    public void setUp() {
        Context.gateway = new MockGateway();
        user = new User("User");
        codecast = new Codecast();
        viewLicense = new License(user, codecast);
    }

    @Test
    public void userWithoutViewLicense_cannotViewCodecast() {
        PresentCodecastUseCase useCase = new PresentCodecastUseCase();

        assertFalse(useCase.isLicensedToViewCodecast(user, codecast));
    }

    @Test
    public void userWithLicense_canViewCodecast() {
        PresentCodecastUseCase useCase = new PresentCodecastUseCase();
        Context.gateway.save(viewLicense);
        assertTrue(useCase.isLicensedToViewCodecast(user, codecast));
    }

    @Test
    public void userWithoutLicense_cannotViewOtherUserCodecast() {
        User otherUser = new User("otherUser");
        Context.gateway.save(otherUser);

        PresentCodecastUseCase useCase = new PresentCodecastUseCase();
        Context.gateway.save(viewLicense);
        assertFalse(useCase.isLicensedToViewCodecast(otherUser, codecast));
    }
}
