package com.jrsoft.learning.cleancoderscom;

import com.jrsoft.learning.cleancoderscom.License.LicenseType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.jrsoft.learning.cleancoderscom.License.LicenseType.DOWNLOADING;
import static com.jrsoft.learning.cleancoderscom.License.LicenseType.VIEWING;

public class PresentCodecastUseCase {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

    public List<PresentableCodecast> presentCodecasts(User user) {
        ArrayList<PresentableCodecast> presentableCodecasts = new ArrayList<>();
        List<Codecast> allCodecasts = Context.gateway.findAllCodecastsSortedChronologically();

        for (Codecast codecast : allCodecasts)
            presentableCodecasts.add(formatCodecast(user, codecast));

        return presentableCodecasts;
    }

    private PresentableCodecast formatCodecast(User user, Codecast codecast) {
        PresentableCodecast pcc = new PresentableCodecast();
        pcc.title = codecast.getTitle();
        pcc.publicationDate = dateFormat.format(codecast.getPublicationDate());
        pcc.isViewable = isLicenseFor(VIEWING, user, codecast);
        pcc.isDownloadable = isLicenseFor(DOWNLOADING, user, codecast);
        return pcc;
    }

    public boolean isLicenseFor(LicenseType licenseType, User user, Codecast codecast) {
        List<License> licenses = Context.gateway.findLicensesForUserAndCodecast(user, codecast);
        for (License license : licenses) {
            if (license.getType() == licenseType)
                return true;
        }
        return false;
    }
}
