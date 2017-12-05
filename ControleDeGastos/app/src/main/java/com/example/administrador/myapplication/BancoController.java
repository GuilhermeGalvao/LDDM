package com.example.administrador.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by julio on 04/12/17.
 */

public class BancoController {
    private SQLiteDatabase db;
    private BancoDeDados banco;

    public BancoController(Context context) {
        banco = new BancoDeDados(context);
    }
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {banco.ID, banco.VALOR };
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABLE, campos, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String inserirDado(double valor){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoDeDados.VALOR, valor);

        resultado = db.insert(BancoDeDados.TABLE, null, valores);
        db.close();

        if(resultado == 1){
            return "Erro ao inserir registro";
        }else{
            return  "Registro inserido com sucesso";
        }
    }
    public double getContactsCount() {
        db = banco.getReadableDatabase();
        Cursor cursor;
        double i ;
        String countQuery = "SELECT SUM(valor) FROM " + "valores";
        cursor =  db.rawQuery(countQuery, null);
        cursor.moveToFirst();

        if(cursor.moveToFirst())
            i = cursor.getInt(0);
        else
            i = -1;


        cursor.close();//
        db.close();

        // return count
        return i;
    }



}
