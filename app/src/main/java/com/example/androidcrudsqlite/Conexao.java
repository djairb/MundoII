package com.example.androidcrudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 5;

    public Conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table aluno(id integer primary key autoincrement," +
                "nome varchar(50), cpf varchar(50), telefone varchar(50), senha varchar(50))");
        db.execSQL("create table profissional(id integer primary key autoincrement," +
                "nome varchar(50), cpf varchar(50), telefone varchar(50), senha varchar(50), funcao varchar(50))");
        db.execSQL("create table problema(id integer primary key autoincrement," +
                "cpfAluno varchar(50), tipo varchar(50), textoDescricao varchar(300), prazo varchar(50))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("alter table aluno add column senha varchar(50)");


    }
}
