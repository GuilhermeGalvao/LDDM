package com.example.administrador.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julio on 04/12/17.
 */

public class BancoDeDados extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    static final String ID = "_id";
    static final String VALOR = "valor";
    private static final int VERSAO = 1;
    static final String TABLE = "valores";

    public BancoDeDados(Context context){
        super(context, NOME_BANCO, null, VERSAO );
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE +" (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                VALOR + " REAL" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE);
        onCreate(sqLiteDatabase);
    }
}
