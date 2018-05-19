package br.gov.rodacultural.rodacultural.models;

import java.util.List;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class Conversation {
    private long id; /* A partir do ID, puxar todas as mensagens quando solicitado */
    private Message lastMessage; /* Somente para exibição do item na lista de mensagens */
    private User user;
    private List<Message> messages; /* Lista de todas as mensagens */

    public Conversation(Message lastMessage, User user) {
        this.lastMessage = lastMessage;
        this.user = user;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public User getUser() {
        return user;
    }
}
