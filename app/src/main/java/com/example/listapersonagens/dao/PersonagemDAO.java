package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
    private static int contadorDeId = 1;

    //Salvando personagem//
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeId);
        personagens.add(personagemSalvo);
        atualizaId();
    }

    private void atualizaId() {
        contadorDeId++;
    }

    // Editar informações dentro do personagem//
    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);
        }
    }
    //Retorna personagem conforme busca//
    private Personagem buscaPersonagemId(Personagem personagem) {
        for (Personagem p : personagens) {
            if (p.getId() == personagem.getId()) {
                return p;
            }
        }
        return null;
    }

    public List<Personagem> todos() {
        return new ArrayList<>(personagens);
    }


}
