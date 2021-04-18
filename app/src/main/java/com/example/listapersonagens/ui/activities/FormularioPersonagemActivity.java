package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem Personagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        //**Definindo titulo**//
        setTitle("Formulário de Personagens");
        InicializacaoCampos();
        ConfigBotaoAdd();

        //**Fazendo checagem do botão Enviar e criando função Java**//

        Intent dados = getIntent();
        if (dados.hasExtra("personagem")) {
            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
            campoNome.setText(personagem.getNome());
            campoAltura.setText(personagem.getAltura());
            campoNascimento.setText(personagem.getNascimento());
        } else {
            Personagem = new Personagem();

        }

    }

    private void ConfigBotaoAdd() {
        //**Pegando botão para ouvir ações (OnClickListener)**//
        Button botaoSalvar = findViewById(R.id.button_Salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //**Convertendo texts em strings**//
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);
                finish();

                //**iniciando nova activity**//

                //**Toast usando os gets para utilizar variaveis**//

                //**Unindo as coisas e instanciando personagem**//


                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);
            }
        });
    }

    private void InicializacaoCampos() {
        //**Pegando campos Id**//
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }
}