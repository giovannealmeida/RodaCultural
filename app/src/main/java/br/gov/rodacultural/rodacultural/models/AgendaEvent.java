package br.gov.rodacultural.rodacultural.models;

import java.util.Calendar;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class AgendaEvent {

    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private Calendar start, end;

    public AgendaEvent() {
    }

    public AgendaEvent(String name, String description, String imageUrl, Calendar start, Calendar end) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.start = start;
        this.end = end;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }
}
