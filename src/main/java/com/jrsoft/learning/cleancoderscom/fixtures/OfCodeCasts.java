package com.jrsoft.learning.cleancoderscom.fixtures;

import com.jrsoft.learning.cleancoderscom.PresentCodecastUseCase;
import com.jrsoft.learning.cleancoderscom.PresentableCodecast;
import com.jrsoft.learning.cleancoderscom.User;

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
                .forEach(pcc -> queryResponse.add(makeRow(pcc.title, pcc.title, pcc.title, pcc.isViewable, false)));
        return queryResponse;
    }

    private List<Object> makeRow(String title, String picture, String description, boolean viewable, boolean downloadable) {
        return list(new Object[]{list(
            list("title", title),
            list("description", description),
            list("viewable", viewable ? "+" : "-"),
            list("downloadable", downloadable ? "+" : "-")
        )});
    }
}
