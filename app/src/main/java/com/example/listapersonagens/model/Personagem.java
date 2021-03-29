package com.example.listapersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {

    //Variaveis "final" não podem ser alteradas quando usado SET.//
/*    private final String nome;
    private final String altura;
    private final String nascimento;*/

    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;


    public Personagem(String nome, String altura, String nascimento) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    //Retornando Nome salvo em lista//
    @NonNull
        @Override
        public String toString(){
            return nome;
        }

        public  void setId(int id){
        this.id = id;

        }

        public int getId(){
            return id;
        }

        /* }

    public String getNome() {


        return nome;
    }

    public String getAltura() {


        return altura;
    }

    public String getNascimento() {


        return nascimento;
    }*/
}


