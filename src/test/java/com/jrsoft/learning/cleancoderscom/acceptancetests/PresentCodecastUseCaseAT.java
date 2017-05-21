package com.jrsoft.learning.cleancoderscom.acceptancetests;

import com.jrsoft.learning.cleancoderscom.fixtures.CodecastPresentation;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PresentCodecastUseCaseAT {

    private CodecastPresentation codecastPresentationFixture;

    @Before
    public void setUp() {
        this.codecastPresentationFixture = new CodecastPresentation();
    }

    @Test
    public void presentNoCodecastsScenario() {
        givenNoCodecasts();
        givenUser("U");
        withUserLoggedIn("U");
        thenTheFollowingCodecastWillBePresentedTo("U");
        thereWillBeNoCodecastsPresented();
    }

    @Test
    public void presentViewableCodecastsInChronologicalOrderScenario() {
        givenUser("U");
        withUserLoggedIn("U");
        andTheLicensceForUserAbleToViewCodecast("U", "A");
    }

    private void givenNoCodecasts() {
        assertTrue(codecastPresentationFixture.clearCodecasts());
    }

    private void givenUser(String userName) {
        assertTrue(codecastPresentationFixture.addUser(userName));
    }

    private void withUserLoggedIn(String userName) {
        assertTrue(codecastPresentationFixture.loginUser(userName));
    }

    private void thenTheFollowingCodecastWillBePresentedTo(String userName) {
        assertThat(codecastPresentationFixture.presentationUser(), is(userName));
    }

    private void thereWillBeNoCodecastsPresented() {
        assertThat(codecastPresentationFixture.countOfCodecastsPresented(), is(0));
    }

    private void andTheLicensceForUserAbleToViewCodecast(String userName, String codecastTitle) {
        assertTrue(codecastPresentationFixture.createLicenceForViewing(userName, codecastTitle));
    }
}
