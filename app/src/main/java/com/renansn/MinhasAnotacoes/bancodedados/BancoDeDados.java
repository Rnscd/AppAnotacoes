package com.renansn.MinhasAnotacoes.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDeDados {
    public SQLiteDatabase banco;
    public GerenciarBanco gerenciarBanco;

    public BancoDeDados(Context context) {
        gerenciarBanco = new GerenciarBanco(context);
    }
    public boolean criarAnotacao(String titulo, String conteudo){
        banco = gerenciarBanco.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("titulo", titulo);
        valores.put("conteudo", conteudo);
        long resultado = banco.insert("anotacoes", null, valores);
        banco.close();
        return resultado > 0;
    }
    public Cursor obterAnotacoes(){
        String[] campos = {"_id", "titulo"};
        SQLiteDatabase db = gerenciarBanco.getReadableDatabase();
        Cursor cursor = db.query("anotacoes", campos, null, null, null, null, "titulo ASC");
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


}

