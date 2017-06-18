package com.jrsoft.learning.cleancoderscom.business.acceptancetests;

import com.jrsoft.learning.cleancoderscom.business.Context;
import com.jrsoft.learning.cleancoderscom.business.GateKeeper;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles.InMemoryCodecastGateway;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles.InMemoryLicenseGateway;
import com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles.InMemoryUserGateway;

public class TestSetup {
    public static void setupContext() {
        Context.userGateway = new InMemoryUserGateway();
        Context.licenseGateway = new InMemoryLicenseGateway();
        Context.codecastGateway = new InMemoryCodecastGateway();
        Context.gateKeeper = new GateKeeper();
    }
}
