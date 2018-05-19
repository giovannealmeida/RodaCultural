package br.gov.rodacultural.rodacultural.models;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class Message {
    private long id;
    private String text;
    private User user; /* Quem enviou a mensagem*/
    private String timestamp;

    public Message(String text, User user, String timestamp) {
        this.text = text;
        this.user = user;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
