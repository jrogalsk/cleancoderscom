package com.jrsoft.learning.cleancoderscom.fixtures;

import com.jrsoft.learning.cleancoderscom.Codecast;
import com.jrsoft.learning.cleancoderscom.Context;

public class GivenCodecasts {
    private String title;
    private String publicationDate;

    public GivenCodecasts() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublished(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void execute() {
        Codecast codecast = new Codecast();
        codecast.setTitle(title);
        codecast.setPublicationDate(publicationDate);
        Context.gateway.save(codecast);
    }
}
