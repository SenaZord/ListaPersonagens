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

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Formulário de Personagens";
    public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    //**OnCreate sendo construido**//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        //**Definindo titulo**//
        setTitle(TITULO_APPBAR);
        InicializacaoCampos();
        ConfigBotaoAdd();

        //**Fazendo checagem do botão Enviar e criando função Java**//

        CarregaPersonagem();

    }

    private void CarregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();

        }
    }
    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void ConfigBotaoAdd() {
        //**Pegando botão para ouvir ações (OnClickListener)**//
            Button botaoSalvar = findViewById(R.id.button_Salvar);
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finalizarFormulario();
                }
            });
            }


    private void finalizarFormulario(){
        preencherPersonagem();
        //Checagem de id valido para salvar//
        if(personagem.IdInvalido()){
            dao.editar(personagem);
            finish();
        }
        else {
            dao.salva(personagem);
        }
        finish();
    }

    private void InicializacaoCampos() {
        //**Pegando campos Id**//
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }

        //Pegando informações para preenchimento do personagem//
    private void preencherPersonagem(){
        String nome = campoNome.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        String altura = campoAltura.getText().toString();

        personagem.setNome(nome);
        personagem.setNascimento(nascimento);
        personagem.setAltura(altura);
    }
}