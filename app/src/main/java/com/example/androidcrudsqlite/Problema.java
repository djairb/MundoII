package com.example.androidcrudsqlite;

public class Problema {


    private Integer id;

    private String cpfAluno;
    private String tipo;

    private String textoDescricao;
    private String prazo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTextoDescricao() {
        return textoDescricao;
    }

    public void setTextoDescricao(String textoDescricao) {
        this.textoDescricao = textoDescricao;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    @Override
    public String toString(){
        return tipo;
    }


}
