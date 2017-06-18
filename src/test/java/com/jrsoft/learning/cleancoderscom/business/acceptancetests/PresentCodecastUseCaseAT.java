package com.jrsoft.learning.cleancoderscom.business.acceptancetests;

import com.jrsoft.learning.cleancoderscom.business.acceptancetests.fixtures.CodecastPresentation;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.fixtures.GivenCodecasts;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.fixtures.OfCodeCasts;
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
        thenTheFollowingCodecastWillBePresentedFor("U");
        thereWillBeNoCodecastsPresented();
    }

    @Test
    public void presentViewableCodecastsInChronologicalOrderScenario() {
        givenCodecast("A", "3/1/2014");
        givenCodecast("B", "3/2/2014");
        givenCodecast("C", "2/18/2014");
        givenUser("U");
        withUserLoggedIn("U");
        andTheLicensceForUserAbleToViewCodecast("U", "A");
        thenTheFollowingCodecastWillBePresentedFor("U");
        asOrderedCodecasts(); // <- should add assertions on expected results
    }

    @Test
    public void presentDownloadableCodecastsScenario() {
        givenCodecast("A", "3/1/2014");
        givenCodecast("B", "3/2/2014");
        givenCodecast("C", "2/18/2014");
        givenUser("U");
        withUserLoggedIn("U");
        andWithLicenseForUserAbleToDownloadCodecast("U", "A");
        thenTheFollowingCodecastWillBePresentedFor("U");
        asOrderedCodecasts(); // <- should add assertions on expected results
    }

    private void asOrderedCodecasts() {
        OfCodeCasts ofCodeCasts = new OfCodeCasts();
        ofCodeCasts.query();
    }

    private void givenCodecast(String title, String published) {
        GivenCodecasts givenCodecasts = new GivenCodecasts();
        givenCodecasts.setTitle(title);
        givenCodecasts.setPublished(published);
        givenCodecasts.execute();
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

    private void thenTheFollowingCodecastWillBePresentedFor(String userName) {
        assertThat(codecastPresentationFixture.presentationUser(), is(userName));
    }

    private void thereWillBeNoCodecastsPresented() {
        assertThat(codecastPresentationFixture.countOfCodecastsPresented(), is(0));
    }

    private void andTheLicensceForUserAbleToViewCodecast(String userName, String codecastTitle) {
        assertTrue(codecastPresentationFixture.createLicenceForViewing(userName, codecastTitle));
    }

    private void andWithLicenseForUserAbleToDownloadCodecast(String user, String codecast) {
        assertTrue(codecastPresentationFixture.createLicenceForDownloading(user, codecast));
    }
}
