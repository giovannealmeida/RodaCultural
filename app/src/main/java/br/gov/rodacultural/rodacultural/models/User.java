package br.gov.rodacultural.rodacultural.models;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class User {
    private long id;
    private String name;
    private String picUrl;
    private boolean isSender;

    public User(long id, String name, String picUrl, boolean isSender) {
        this.id = id;
        this.name = name;
        this.picUrl = picUrl;
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
