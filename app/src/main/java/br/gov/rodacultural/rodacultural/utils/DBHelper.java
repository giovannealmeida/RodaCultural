package br.gov.rodacultural.rodacultural.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Giovanne on 01/07/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    //Constantes do banco
    private static final String DB_NAME = "roda_cultural_db";
    private static final int DB_VERSION = 1;

    //Constantes das tabelas
    public static final String TBL_USER = "user";
    private SQLiteDatabase database;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        database = getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criação da tabela de usuários
        db.execSQL("CREATE TABLE " + TBL_USER + " (" +
                " name TEXT NOT NULL," +
                " email TEXT NOT NULL," +
                " password TEXT," +
                " token TEXT NOT NULL); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TBL_USER);

        onCreate(db);
    }

    /**
     * Deleta todos os registros de usuários
     */
    public void clearUsers() {
        getDatabase().execSQL("DELETE FROM " + DBHelper.TBL_USER);
    }

}
