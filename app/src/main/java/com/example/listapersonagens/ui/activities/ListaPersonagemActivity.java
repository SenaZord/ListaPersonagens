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

public class ListaPersonagemActivity extends AppCompatActivity {

    private  final PersonagemDAO dao = new PersonagemDAO();

    // Criando override para ListaPersonagem//
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //Definindo títutlo//
        setTitle("Lista de Personagens");
        dao.salva(new Personagem("Ken", "1,80", "02041979"));
        dao.salva(new Personagem("Ryu", "1,80", "02041979"));

        //Listando array//
        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex, Ken, Ryu"));

        //Pegando FloatingActionButton//
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

        //Listando array//

    }

    // Proteção para impedir de apagar ao apertar Back//
    @Override
    protected void onResume() {
        super.onResume();

        //Referenciando index de Dao//

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);

        //Refereciando dao.todos para personagens//
        List<Personagem> personagens = dao.todos();
        //Definindo personagens na lista//
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Metodo seleção de personagens//
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
