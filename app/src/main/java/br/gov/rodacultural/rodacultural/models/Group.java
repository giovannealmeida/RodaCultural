package br.gov.rodacultural.rodacultural.models;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class Group {
    private long id;
    private String name;
    private String description;

    public Group(long id, String name, String description) {

        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
