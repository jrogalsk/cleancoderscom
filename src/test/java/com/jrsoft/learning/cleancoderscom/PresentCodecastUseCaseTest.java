package com.jrsoft.learning.cleancoderscom;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.jrsoft.learning.cleancoderscom.License.LicenseType.DOWNLOADING;
import static com.jrsoft.learning.cleancoderscom.License.LicenseType.VIEWING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PresentCodecastUseCaseTest {

    private User user;
    private Codecast codecast;
    private PresentCodecastUseCase useCase;

    @Before
    public void setUp() {
        Context.gateway = new MockGateway();
        user = Context.gateway.save(new User("User"));
        codecast = Context.gateway.save(new Codecast());
        useCase = new PresentCodecastUseCase();
    }

    @Test
    public void userWithoutViewLicense_cannotViewCodecast() {
        assertFalse(useCase.isLicenseFor(VIEWING, user, codecast));
    }

    @Test
    public void userWithLicense_canViewCodecast() {
        License viewLicense = new License(VIEWING, user, codecast);
        Context.gateway.save(viewLicense);
        assertTrue(useCase.isLicenseFor(VIEWING, user, codecast));
    }

    @Test
    public void userWithoutLicense_cannotViewOtherUserCodecast() {
        User otherUser = Context.gateway.save(new User("otherUser"));

        License viewLicense = new License(VIEWING, user, codecast);
        Context.gateway.save(viewLicense);
        assertFalse(useCase.isLicenseFor(VIEWING, otherUser, codecast));
    }

    @Test
    public void presentingNoCodecasts() {
        // this is generally wrong to do. We should not undo something that is done is setup.
        Context.gateway.delete(codecast);

        assertTrue(useCase.presentCodecasts(user).isEmpty());
    }

    @Test
    public void presentOneCodecast() {
        codecast.setTitle("Some Title");
        Date now = new GregorianCalendar(2014, 4, 19).getTime();
        codecast.setPublicationDate(now);
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        assertThat(presentableCodecasts.size(), is(1));
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertThat(presentableCodecast.title, is("Some Title"));
        assertThat(presentableCodecast.publicationDate, is("5/19/2014"));
    }

    @Test
    public void presentedCodecastIsNotViewableIfNoLicense() {
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertFalse(presentableCodecast.isViewable);
    }

    @Test
    public void presentedCodecastIsViewableIfLicenseExists() {
        Context.gateway.save(new License(VIEWING, user, codecast));
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isViewable);
    }

    @Test
    public void presentedCodecastisDownloadableIfDownloadLicenseExists() {
        License downloadLicense = new License(DOWNLOADING, user, codecast);
        Context.gateway.save(downloadLicense);
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isDownloadable);
        assertFalse(presentableCodecast.isViewable);
    }

}
