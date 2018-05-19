package br.gov.rodacultural.rodacultural.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Giovanne on 30/06/2016.
 */
public class SessionHelper {

    private Context context;

    public SessionHelper(Context context) {
        this.context = context;
    }

    public boolean isLogged() {
        boolean isLogged = false;
        //Se houver algum token de usuário salvo, então exsite um usuário logado
        DBHelper helper = new DBHelper(context);
        Cursor cursor = helper.getDatabase().query(DBHelper.TBL_USER, null, null, null, null, null, null, null);

        if (cursor.getCount() == 1) {
            isLogged = true;
        } else { //Se houver mais de um resgistro de usuário, algo deu errado. Força login novamente.
            logout();
        }

        cursor.close();
        helper.close();

        return isLogged;
    }

    public void logout() {
        new DBHelper(context).clearUsers();
    }

    public String getUserToken() {
        DBHelper helper = new DBHelper(context);
        Cursor cursor = helper.getDatabase().query(DBHelper.TBL_USER, new String[]{"token"}, null, null, null, null, null, null);

        String userToken = null;

        if (cursor.getCount() == 1) {
            cursor.moveToFirst();

            userToken = cursor.getString(0);
        } else {
            logout();
        }
        cursor.close();
        helper.close();

        return userToken;
    }

    public void saveUser(JSONObject userData, String password) {
        try {
            DBHelper dbHelper = new DBHelper(context);
            ContentValues values = new ContentValues();

            values.put("token", userData.getString("token"));
            values.put("name", userData.getString("name"));
            values.put("email", userData.getString("email"));
            values.put("password", password);
            dbHelper.getDatabase().insert(DBHelper.TBL_USER, null, values);
            dbHelper.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
