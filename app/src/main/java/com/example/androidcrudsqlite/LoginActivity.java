package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText cpfLogin;
    private EditText senhaLogin;
    private Button botaoLogar;
    private Button logarProfissional;
    private Button cadastroProfissonal;
    private ProfissionalDAO profissionalDAO;
    private AlunoDAO alunoDAO;
    private Button realizarCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpfLogin = findViewById(R.id.editTextLoginCpf);
        senhaLogin = findViewById(R.id.editTextLoginSenha);
        botaoLogar = findViewById(R.id.botaoLogin);
        logarProfissional = findViewById(R.id.loginProfissional);
        realizarCadastro = findViewById(R.id.botaoEfetuarCadastro);
        cadastroProfissonal = findViewById(R.id.botaoProfissional);



        profissionalDAO = new ProfissionalDAO(this);
        alunoDAO = new AlunoDAO(this);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificarLoginAluno(cpfLogin.getText().toString(), senhaLogin.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Login efetuado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new Intent(LoginActivity.this, ListarAlunosActivity.class));
                    intent.putExtra("cpf",cpfLogin.getText().toString());
                    startActivity(intent);
                }
            }
        });

        logarProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificarLoginProfissional(cpfLogin.getText().toString(), senhaLogin.getText().toString())){
                    Toast.makeText(LoginActivity.this,"TU É PROFISSIONAL, GRAÇAS A DEUS", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new Intent(LoginActivity.this, ListarProblemasActivity.class));
                    intent.putExtra("cpf",cpfLogin.getText().toString());
                    startActivity(intent);
                }
            }
        });

        realizarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroAlunoActivity.class));
            }
        });

        cadastroProfissonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroProfissionalActivity.class));
            }
        });

    }

    public Boolean verificarLoginProfissional(String cpf, String senha){
        Boolean prosseguir = true;
        if(!profissionalDAO.existeCpf(cpf)){
            cpfLogin.setError("Cpf não consta no banco de Dados");
            prosseguir = false;
            return prosseguir;
        }
        if(!profissionalDAO.comparaSenha(cpf, senha)){
            senhaLogin.setError("Senha informada não confere com a cadastrada.");
            prosseguir = false;
            return prosseguir;
        }

        return prosseguir;


    }

    public Boolean verificarLoginAluno(String cpf, String senha){
        Boolean prosseguir = true;
        if(!alunoDAO.existeCpf(cpf)){
            cpfLogin.setError("Cpf não consta no banco de Dados");
            prosseguir = false;
            return prosseguir;
        }
        if(!alunoDAO.comparaSenha(cpf, senha)){
            senhaLogin.setError("Senha informada não confere com a cadastrada.");
            prosseguir = false;
            return prosseguir;
        }

        return prosseguir;


    }





}