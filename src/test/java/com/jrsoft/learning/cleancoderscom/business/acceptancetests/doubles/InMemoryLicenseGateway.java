package com.jrsoft.learning.cleancoderscom.business.acceptancetests.doubles;

import com.jrsoft.learning.cleancoderscom.business.Codecast;
import com.jrsoft.learning.cleancoderscom.business.License;
import com.jrsoft.learning.cleancoderscom.business.LicenseGateway;
import com.jrsoft.learning.cleancoderscom.business.User;

import java.util.List;

public class InMemoryLicenseGateway extends GatewayUtilities<License> implements LicenseGateway {
    @Override
    public List<License> findLicensesForUserAndCodecast(User user, Codecast codecast) {
        throw new UnsupportedOperationException("#findLicensesForUserAndCodecast()");
    }
}
