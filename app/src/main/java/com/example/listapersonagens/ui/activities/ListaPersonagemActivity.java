package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Personagem";
    private final PersonagemDAO dao = new PersonagemDAO();

    // Criando override para ListaPersonagem//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //Definindo títutlo//
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();

    }

    private void configuraFabNovoPersonagem() {
        //Pegando FloatingActionButton//
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormulario();
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

    // Proteção para impedir de apagar ao apertar Back//
    @Override
    protected void onResume() {
        super.onResume();
        //Referenciando index de Dao//

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        //Refereciando dao.todos para personagens//
        final List<Personagem> personagens = dao.todos();
        listaDePersonagens(listaDePersonagens, personagens);

        configuraItemPorClique(listaDePersonagens);


    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Metodo seleção de personagens//
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(position);
                //Log.i("personagem","" + personagemEscolhido);//
                abreFormularioEditar(personagemEscolhido);

            }
        });
    }

    private void abreFormularioEditar(Personagem personagemEscolhido) {
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity(vaiParaFormulario);
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        //Definindo personagens na lista//
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }
}
