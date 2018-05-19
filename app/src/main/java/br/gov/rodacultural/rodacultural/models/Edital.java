package br.gov.rodacultural.rodacultural.models;

import java.util.Calendar;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class Edital {
    private long id;
    private String name;
    private String desciption;
    private Calendar opening;
    private String docUrl;

    public Edital(String name, String desciption, Calendar opening) {
        this.name = name;
        this.desciption = desciption;
        this.opening = opening;
    }

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public Calendar getOpening() {
        return opening;
    }
}
