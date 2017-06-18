package com.jrsoft.learning.cleancoderscom.business.acceptancetests.fixtures;

import com.jrsoft.learning.cleancoderscom.business.Codecast;
import com.jrsoft.learning.cleancoderscom.business.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GivenCodecasts {
    private String title;
    private String publicationDate;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

    public GivenCodecasts() {

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublished(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void execute() {
        try {
            Codecast codecast = new Codecast();
            codecast.setTitle(title);
            codecast.setPublicationDate(dateFormat.parse(publicationDate));
            Context.codecastGateway.save(codecast);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }
}
