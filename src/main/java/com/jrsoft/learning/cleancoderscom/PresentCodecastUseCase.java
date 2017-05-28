package com.jrsoft.learning.cleancoderscom;

import java.util.ArrayList;
import java.util.List;

public class PresentCodecastUseCase {
    public List<PresentableCodecast> presentCodecasts(User loggedInUser) {
        final ArrayList<PresentableCodecast> presentableCodecasts = new ArrayList<>();
        List<Codecast> allCodecasts = Context.gateway.findAllCodecasts();
        for (Codecast codecast : allCodecasts) {
            PresentableCodecast cc = new PresentableCodecast();
            cc.isViewable = isLicensedToViewCodecast(loggedInUser, codecast);
            cc.title = codecast.getTitle();
            cc.publicationDate = codecast.getPublicationDate();
            presentableCodecasts.add(cc);
        }

        return presentableCodecasts;
    }

    public boolean isLicensedToViewCodecast(User user, Codecast codecast) {
        List<License> licenses = Context.gateway.findLicensesForUserAndCodecast(user, codecast);
        return !licenses.isEmpty();
    }
}
