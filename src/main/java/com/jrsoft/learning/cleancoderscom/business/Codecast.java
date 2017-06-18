package com.jrsoft.learning.cleancoderscom.business;

import java.util.Date;

public class Codecast extends Entity {
    private String title;
    private Date publicationDate = new Date();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSame(Codecast codecast) {
        return true;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
}
