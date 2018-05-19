package br.gov.rodacultural.rodacultural.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import br.gov.rodacultural.rodacultural.models.User;

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

    public void saveUser(User user, String token, String password) {
        DBHelper dbHelper = new DBHelper(context);
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", password);
        values.put("token", token);
        dbHelper.getDatabase().insert(DBHelper.TBL_USER, null, values);
        dbHelper.close();
    }
}
