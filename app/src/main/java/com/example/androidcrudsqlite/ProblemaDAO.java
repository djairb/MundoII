package com.example.androidcrudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProblemaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProblemaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();

    }


    private String cpfAluno;
    private String tipo;

    private String textoDescricao;
    private String prazo;

    //create
    public long inserir(Problema problema){
        ContentValues values = new ContentValues();
        values.put("cpfAluno", problema.getCpfAluno());
        values.put("tipo", problema.getTipo());
        values.put("textoDescricao", problema.getTextoDescricao());
        values.put("prazo", problema.getPrazo());
        return banco.insert("problema", null, values);

    }


    public Problema retornaProblema(Integer id){
        Problema problema = new Problema();
        boolean resposta = false;
        String where = "SELECT * FROM problema WHERE id = '" + id + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            problema.setId(cursor.getInt(0));
            problema.setCpfAluno(cursor.getString(1));
            problema.setTipo(cursor.getString(2));
            problema.setTextoDescricao(cursor.getString(3));
            problema.setPrazo(cursor.getString(4));
        }
        return problema;


    }

    public List<Problema> obterTodos(){
        List<Problema> problemaRetorno = new ArrayList<>();
        String where = "SELECT * FROM problema";
        Cursor cursor = banco.rawQuery(where, null);

        while (cursor.moveToNext()){
            Problema problemaLista = new Problema();
            problemaLista.setId(cursor.getInt(0));
            problemaLista.setCpfAluno(cursor.getString(1));
            problemaLista.setTipo(cursor.getString(2));
            problemaLista.setTextoDescricao(cursor.getString(3));
            problemaLista.setPrazo(cursor.getString(4));
            problemaRetorno.add(problemaLista);
        }
        //add verificação pra caso ela esteja vazia
        return problemaRetorno;

    }

}

    /*

    //update
    public void atualizar(Profissional profissional){
        ContentValues values = new ContentValues();
        values.put("nome", profissional.getNome());
        values.put("cpf", profissional.getCpf());
        values.put("telefone", profissional.getTelefone());
        values.put("senha", profissional.getSenha());
        banco.update("profissional",values, "id = ?", new String[]{String.valueOf(profissional.getId())});

    }

    //delete
    public void deletar(String cpf){
        Profissional profissional = retornaProfissional(cpf);
        banco.delete("profissional", "id = ?", new String[]{String.valueOf(profissional.getId())});
    }

     */








