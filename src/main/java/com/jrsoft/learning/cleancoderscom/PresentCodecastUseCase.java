package com.jrsoft.learning.cleancoderscom;

import java.util.ArrayList;
import java.util.List;

public class  PresentCodecastUseCase {
    public List<PresentableCodecast> presentCodecasts(User loggedInUser) {
        return new ArrayList<PresentableCodecast>();
    }

    public boolean isLicensedToViewCodecast(User user, Codecast codecast) {
        List<License> licenses = Context.gateway.findLicensesForUserAndCodecast(user, codecast);
        return !licenses.isEmpty();
    }
}
