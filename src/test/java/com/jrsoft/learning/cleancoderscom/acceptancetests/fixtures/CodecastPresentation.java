package com.jrsoft.learning.cleancoderscom.acceptancetests.fixtures;

import com.jrsoft.learning.cleancoderscom.Codecast;
import com.jrsoft.learning.cleancoderscom.Context;
import com.jrsoft.learning.cleancoderscom.GateKeeper;
import com.jrsoft.learning.cleancoderscom.License;
import com.jrsoft.learning.cleancoderscom.MockGateway;
import com.jrsoft.learning.cleancoderscom.PresentCodecastUseCase;
import com.jrsoft.learning.cleancoderscom.PresentableCodecast;
import com.jrsoft.learning.cleancoderscom.User;

import java.util.ArrayList;
import java.util.List;

import static com.jrsoft.learning.cleancoderscom.License.LicenseType.DOWNLOADING;
import static com.jrsoft.learning.cleancoderscom.License.LicenseType.VIEWING;
import static java.util.Objects.nonNull;

public class CodecastPresentation {
    private PresentCodecastUseCase useCase = new PresentCodecastUseCase();
    public static GateKeeper gateKeeper = new GateKeeper();

    public CodecastPresentation() {
        Context.gateway = new MockGateway();
    }

    public boolean addUser(String username) {
        Context.gateway.save(new User(username));
        return true;
    }

    public boolean loginUser(String username) {
        User user = Context.gateway.findUser(username);
        if (nonNull(user)) {
            gateKeeper.setLoggedInUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean createLicenceForViewing(String userName, String codecastTitle) {
        User user = Context.gateway.findUser(userName);
        Codecast codecast = Context.gateway.findCodecastByTitle(codecastTitle);
        License license = new License(VIEWING, user, codecast);
        Context.gateway.save(license);
        return useCase.isLicenseFor(VIEWING, user, codecast);
    }

    public String presentationUser() {
        return gateKeeper.getLoggedInUser().getUserName();
    }

    public boolean clearCodecasts() {
        new ArrayList<>(Context.gateway
                .findAllCodecastsSortedChronologically())
                .forEach(codecast -> Context.gateway.delete(codecast));

        return Context.gateway.findAllCodecastsSortedChronologically().isEmpty();
    }

    public int countOfCodecastsPresented() {
        List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKeeper.getLoggedInUser());
        return presentations.size();
    }

    public boolean createLicenceForDownloading(String userName, String codecastTitle) {
        User user = Context.gateway.findUser(userName);
        Codecast codecast = Context.gateway.findCodecastByTitle(codecastTitle);
        License license = new License(DOWNLOADING, user, codecast);
        Context.gateway.save(license);
        return useCase.isLicenseFor(DOWNLOADING, user, codecast);
    }
}
