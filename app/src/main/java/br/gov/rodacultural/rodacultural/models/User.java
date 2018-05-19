package br.gov.rodacultural.rodacultural.models;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class User {
    private long id;
    private String name;
    private boolean isSender;

    public User(long id, String name, boolean isSender) {
        this.id = id;
        this.name = name;
        this.isSender = isSender;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSender() {
        return isSender;
    }
}
