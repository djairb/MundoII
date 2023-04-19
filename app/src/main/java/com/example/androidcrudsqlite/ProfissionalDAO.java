package com.example.androidcrudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO {




    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProfissionalDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();

    }

    //create
    public long inserir(Profissional profissional){
        ContentValues values = new ContentValues();
        values.put("nome", profissional.getNome());
        values.put("cpf", profissional.getCpf());
        values.put("telefone", profissional.getTelefone());
        values.put("senha", profissional.getSenha());
        values.put("funcao", profissional.getFuncao());
        return banco.insert("profissional", null, values);

    }

    //read
    public boolean existeCpf(String cpf){
        ///Cursor cursor = banco.query("aluno", new String[]{"cpf"}, "cpf = ?", new String[]{cpf}, null, null, null);
        boolean resposta = false;
        String where = "SELECT cpf FROM profissional WHERE cpf = '" + cpf + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount() > 0){
            resposta = true;
        }
        return resposta;
    }

    public boolean comparaSenha(String cpf, String senha){
        Profissional profissional1 = new Profissional();
        boolean resposta = false;
        String where = "SELECT * FROM profissional WHERE cpf = '" + cpf + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            profissional1.setId(cursor.getInt(0));
            profissional1.setNome(cursor.getString(1));
            profissional1.setCpf(cursor.getString(2));
            profissional1.setTelefone(cursor.getString(3));
            profissional1.setSenha(cursor.getString(4));
            profissional1.setFuncao(cursor.getString(5));
        }
        if(profissional1.getSenha().equals(senha)){
            resposta = true;
        }
        return resposta;
    }

    public void acabarTudo(){
        banco.delete("profissional", null, null);

    }


    public Profissional retornaProfissional(String cpf){
        Profissional profissional = new Profissional();
        String where = "SELECT * FROM profissional WHERE cpf = '" + cpf + "'";
        Cursor cursor = banco.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            profissional.setId(cursor.getInt(0));
            profissional.setNome(cursor.getString(1));
            profissional.setCpf(cursor.getString(2));
            profissional.setTelefone(cursor.getString(3));
            profissional.setSenha(cursor.getString(4));
            profissional.setFuncao(cursor.getString(5));
        }
        return profissional;


    }

    public List<Profissional> obterTodos(){
        List<Profissional> profissionalRetorno = new ArrayList<>();
        String where = "SELECT * FROM profissional";
        Cursor cursor = banco.rawQuery(where, null);

        while (cursor.moveToNext()){
            Profissional profissionalLista = new Profissional();
            profissionalLista.setId(cursor.getInt(0));
            profissionalLista.setNome(cursor.getString(1));
            profissionalLista.setCpf(cursor.getString(2));
            profissionalLista.setTelefone(cursor.getString(3));
            profissionalLista.setSenha(cursor.getString(4));
            profissionalLista.setFuncao(cursor.getString(5));
            profissionalRetorno.add(profissionalLista);
        }
        //add verificação pra caso ela esteja vazia
        return profissionalRetorno;

    }

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






}
