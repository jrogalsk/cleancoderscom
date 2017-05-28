package com.jrsoft.learning.cleancoderscom;

public class Codecast extends Entity {
    private String title;
    private String publicationDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSame(Codecast codecast) {
        return true;
    }

    public String getPublicationDate() {
        return publicationDate;
    }
}
