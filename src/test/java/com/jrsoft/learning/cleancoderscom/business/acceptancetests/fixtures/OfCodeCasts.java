package com.jrsoft.learning.cleancoderscom.business.acceptancetests.fixtures;

import com.jrsoft.learning.cleancoderscom.business.PresentCodecastUseCase;
import com.jrsoft.learning.cleancoderscom.business.PresentableCodecast;
import com.jrsoft.learning.cleancoderscom.business.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OfCodeCasts {
    private List<Object> list(Object... objects) {
        return Arrays.asList(objects);
    }

    public List<Object> query() {
        final User loggedInUser = CodecastPresentation.gateKeeper.getLoggedInUser();
        final PresentCodecastUseCase useCase = new PresentCodecastUseCase();
        List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(loggedInUser);
        List<Object> queryResponse = new ArrayList<>();
        presentableCodecasts
                .forEach(pcc -> queryResponse.add(makeRow(pcc)));
        return queryResponse;
    }

    private List<Object> makeRow(PresentableCodecast pc) {
        return list(new Object[]{list(
            list("title", pc.title),
            list("publication date", pc.publicationDate),
            list("picture", pc.title),
            list("description", pc.title),
            list("viewable", pc.isViewable ? "+" : "-"),
            list("downloadable", pc.isDownloadable ? "+" : "-")
        )});
    }
}
