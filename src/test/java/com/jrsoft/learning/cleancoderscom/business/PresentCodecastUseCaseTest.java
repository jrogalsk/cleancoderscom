package com.jrsoft.learning.cleancoderscom.business;

import com.jrsoft.learning.cleancoderscom.business.acceptancetests.TestSetup;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.jrsoft.learning.cleancoderscom.business.License.LicenseType.DOWNLOADING;
import static com.jrsoft.learning.cleancoderscom.business.License.LicenseType.VIEWING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(HierarchicalContextRunner.class)
public class PresentCodecastUseCaseTest {
    private User user;
    private Codecast codecast;
    private PresentCodecastUseCase useCase;

    @Before
    public void setUp() {
        TestSetup.setupContext();
        user = Context.userGateway.save(new User("User"));
        useCase = new PresentCodecastUseCase();
    }

    public class GivenNoCodecasts {
        @Test
        public void nonArePresented(){
            List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);

            assertEquals(0, presentableCodecasts.size());
        }
    }

    public class GivenOneCodecast {
        private Codecast codecast;

        @Before
        public void setupCodecast() {
            codecast = Context.codecastGateway.save(new Codecast());
        }
        @Test
        public void oneIsPresented() {
            codecast.setTitle("Some Title");
            Date now = new GregorianCalendar(2014, 4, 19).getTime();
            codecast.setPublicationDate(now);
            Context.codecastGateway.save(codecast);

            List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);

            assertEquals(1, presentableCodecasts.size());
            PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
            assertEquals("Some Title", presentableCodecast.title);
            assertEquals("5/19/2014", presentableCodecast.publicationDate);
        }
    }

    public class GivenNoLicenses {
        @Test
        public void userCannotViewCodecast() {
            assertFalse(useCase.isLicenseFor(VIEWING, user, codecast));
        }

        @Test
        public void presentedCodecastShowsNotViewable() {
            List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
            PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
            assertFalse(presentableCodecast.isViewable);
        }
    }


    @Test
    public void userWithLicense_canViewCodecast() {
        License viewLicense = new License(VIEWING, user, codecast);
        Context.licenseGateway.save(viewLicense);
        assertTrue(useCase.isLicenseFor(VIEWING, user, codecast));
    }

    @Test
    public void userWithoutLicense_cannotViewOtherUserCodecast() {
        User otherUser = Context.userGateway.save(new User("otherUser"));

        License viewLicense = new License(VIEWING, user, codecast);
        Context.licenseGateway.save(viewLicense);
        assertFalse(useCase.isLicenseFor(VIEWING, otherUser, codecast));
    }

    @Test
    public void presentingNoCodecasts() {
        // this is generally wrong to do. We should not undo something that is done is setup.
        Context.codecastGateway.delete(codecast);

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
    public void presentedCodecastIsViewableIfLicenseExists() {
        Context.licenseGateway.save(new License(VIEWING, user, codecast));
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isViewable);
    }

    @Test
    public void presentedCodecastisDownloadableIfDownloadLicenseExists() {
        License downloadLicense = new License(DOWNLOADING, user, codecast);
        Context.licenseGateway.save(downloadLicense);
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(user);
        PresentableCodecast presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isDownloadable);
        assertFalse(presentableCodecast.isViewable);
    }

}
