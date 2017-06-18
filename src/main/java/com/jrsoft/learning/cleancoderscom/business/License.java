package com.jrsoft.learning.cleancoderscom.business;

public class License extends Entity {
    public enum LicenseType {VIEWING, DOWNLOADING}

    private License.LicenseType type;
    private User user;
    private Codecast codecast;

    public License(License.LicenseType type, User user, Codecast codecast) {
        this.type = type;
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

    public License.LicenseType getType() {
        return this.type;
    }
}
