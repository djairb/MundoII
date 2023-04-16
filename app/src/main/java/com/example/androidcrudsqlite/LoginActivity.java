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
    private AlunoDAO alunoDAO;
    private Button realizarCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpfLogin = findViewById(R.id.editTextLoginCpf);
        senhaLogin = findViewById(R.id.editTextLoginSenha);
        botaoLogar = findViewById(R.id.botaoLogin);
        realizarCadastro = findViewById(R.id.botaoEfetuarCadastro);

        alunoDAO = new AlunoDAO(this);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificarLogin(cpfLogin.getText().toString(), senhaLogin.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Login efetuado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new Intent(LoginActivity.this, ListarAlunosActivity.class));
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

    }

    public Boolean verificarLogin(String cpf, String senha){
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