package com.jrsoft.learning.cleancoderscom.business;

import java.util.List;

public interface LicenseGateway {
    List<License> findLicensesForUserAndCodecast(User user, Codecast codecast);

    void save(License viewLicense);
}
