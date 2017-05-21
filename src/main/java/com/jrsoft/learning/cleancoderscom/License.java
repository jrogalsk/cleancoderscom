package com.jrsoft.learning.cleancoderscom;

public class License {
    private User user;
    private Codecast codecast;

    public License(User user, Codecast codecast) {
        this.user = user;
        this.codecast = codecast;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Codecast getCodecast() {
        return codecast;
    }

    public void setCodecast(Codecast codecast) {
        this.codecast = codecast;
    }
}
