package br.com.alura.trabalho1_dcc196.Helper;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Filipe on 24/11/2017.
 */

public class DbHelper {

    private static SQLiteDatabase db;

    public static SQLiteDatabase getInstance() {
        if(db == null) {
            db = SQLiteDatabase.openDatabase("Evento",null,0);
        }
        return db;
    }
}
