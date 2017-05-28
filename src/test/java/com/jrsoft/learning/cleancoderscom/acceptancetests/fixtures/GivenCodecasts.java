package com.jrsoft.learning.cleancoderscom.acceptancetests.fixtures;

import com.jrsoft.learning.cleancoderscom.Codecast;
import com.jrsoft.learning.cleancoderscom.Context;

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
            Context.gateway.save(codecast);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
    }
}
