package com.jrsoft.learning.cleancoderscom.business.acceptancetests;

import com.jrsoft.learning.cleancoderscom.business.Codecast;
import com.jrsoft.learning.cleancoderscom.business.Context;
import com.jrsoft.learning.cleancoderscom.business.GateKeeper;
import com.jrsoft.learning.cleancoderscom.business.License;
import com.jrsoft.learning.cleancoderscom.business.User;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles.InMemoryCodecastGateway;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles.InMemoryLicenseGateway;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles.InMemoryUserGateway;

import java.util.Date;

import static com.jrsoft.learning.cleancoderscom.business.License.LicenseType.VIEWING;

public class TestSetup {
    public static void setupContext() {
        Context.userGateway = new InMemoryUserGateway();
        Context.licenseGateway = new InMemoryLicenseGateway();
        Context.codecastGateway = new InMemoryCodecastGateway();
        Context.gateKeeper = new GateKeeper();
    }

    public static void setupSampleDate() {
        setupContext();

        User bob = new User("Bob");
        User micah = new User("Micah");

        Codecast e1 = new Codecast();
        e1.setTitle("Episode 1 - The Beginning");
        e1.setPublicationDate(new Date());

        Codecast e2 = new Codecast();
        e2.setTitle("Episode 2 - The Continuation");
        e2.setPublicationDate(new Date(e1.getPublicationDate().getTime() + 1));

        License bobE1 = new License(VIEWING, bob, e1);
        License bobE2 = new License(VIEWING, bob, e2);

        Context.userGateway.save(bob);
        Context.userGateway.save(micah);
    }
}
