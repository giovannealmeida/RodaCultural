package br.gov.rodacultural.rodacultural.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Giovanne on 18/05/2018.
 */

public class User {
    private long id;
    private String name;
    private String email;
    private String picUrl;
    private boolean isSender;

    public User(JSONObject json){
        try {
            id = json.getInt("id");
            name = json.getString("name");
            email = json.getString("email");
            picUrl = json.getString("picUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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

    public String getEmail() {
        return email;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public boolean isSender() {
        return isSender;
    }
}
