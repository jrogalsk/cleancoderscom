package com.jrsoft.learning.cleancoderscom.business.acceptancetests.fixtures;

import com.jrsoft.learning.cleancoderscom.business.Codecast;
import com.jrsoft.learning.cleancoderscom.business.Context;
import com.jrsoft.learning.cleancoderscom.business.GateKeeper;
import com.jrsoft.learning.cleancoderscom.business.License;
import com.jrsoft.learning.cleancoderscom.business.PresentCodecastUseCase;
import com.jrsoft.learning.cleancoderscom.business.PresentableCodecast;
import com.jrsoft.learning.cleancoderscom.business.User;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.TestSetup;

import java.util.ArrayList;
import java.util.List;

import static com.jrsoft.learning.cleancoderscom.business.License.LicenseType.DOWNLOADING;
import static com.jrsoft.learning.cleancoderscom.business.License.LicenseType.VIEWING;
import static java.util.Objects.nonNull;

public class CodecastPresentation {
    private PresentCodecastUseCase useCase = new PresentCodecastUseCase();
    public static GateKeeper gateKeeper = new GateKeeper();

    public CodecastPresentation() {
        new TestSetup();
    }

    public boolean addUser(String username) {
        Context.userGateway.save(new User(username));
        return true;
    }

    public boolean loginUser(String username) {
        User user = Context.userGateway.findUser(username);
        if (nonNull(user)) {
            gateKeeper.setLoggedInUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean createLicenceForViewing(String userName, String codecastTitle) {
        User user = Context.userGateway.findUser(userName);
        Codecast codecast = Context.codecastGateway.findCodecastByTitle(codecastTitle);
        License license = new License(VIEWING, user, codecast);
        Context.licenseGateway.save(license);
        return useCase.isLicenseFor(VIEWING, user, codecast);
    }

    public String presentationUser() {
        return gateKeeper.getLoggedInUser().getUserName();
    }

    public boolean clearCodecasts() {
        new ArrayList<>(Context.codecastGateway
                .findAllCodecastsSortedChronologically())
                .forEach(codecast -> Context.codecastGateway.delete(codecast));

        return Context.codecastGateway.findAllCodecastsSortedChronologically().isEmpty();
    }

    public int countOfCodecastsPresented() {
        List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKeeper.getLoggedInUser());
        return presentations.size();
    }

    public boolean createLicenceForDownloading(String userName, String codecastTitle) {
        User user = Context.userGateway.findUser(userName);
        Codecast codecast = Context.codecastGateway.findCodecastByTitle(codecastTitle);
        License license = new License(DOWNLOADING, user, codecast);
        Context.licenseGateway.save(license);
        return useCase.isLicenseFor(DOWNLOADING, user, codecast);
    }
}
