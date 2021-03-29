package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    private  final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle("Lista de Personagens");
        dao.salva(new Personagem("Ken", "1,80", "02041979"));
        dao.salva(new Personagem("Ryu", "1,80", "02041979"));



        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex, Ken, Ryu"));

        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });


/*        TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/
    }

    @Override
    protected void onResume() {
        super.onResume();


        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        List<Personagem> personagens = dao.todos();
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personagem personagemEscolhido = personagens.get(position);
                //Log.i("personagem","" + personagemEscolhido);
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);

            }
        });


    }
}
