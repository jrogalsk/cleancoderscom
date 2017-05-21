package com.jrsoft.learning.cleancoderscom.fixtures;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CodecastPresentationTest {

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

    private void givenNoCodecasts() {
        assertTrue(codecastPresentationFixture.clearCodecasts());
    }

    private void givenUser(String username) {
        assertTrue(codecastPresentationFixture.addUser(username));
    }

    private void withUserLoggedIn(String userName) {
        assertTrue(codecastPresentationFixture.loginUser(userName));
    }

    private void thenTheFollowingCodecastWillBePresentedTo(String username) {
        assertThat(codecastPresentationFixture.presentationUser(), is(username));
    }

    private void thereWillBeNoCodecastsPresented() {
        assertThat(codecastPresentationFixture.countOfCodecastsPresented(), is(0));
    }
}
