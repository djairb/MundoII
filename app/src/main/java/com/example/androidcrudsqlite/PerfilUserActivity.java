package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PerfilUserActivity extends AppCompatActivity {

    private TextView perfilNome;
    private TextView perfilId;
    private TextView perfilTelefone;
    private Button botaoVoltar;
    private AlunoDAO alunoDAO;
    private Aluno aluno;
    private String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_user);

        perfilNome = findViewById(R.id.perfilNomeId);
        perfilId = findViewById(R.id.perfilIdId);
        perfilTelefone = findViewById(R.id.perfilTelefoneID);
        botaoVoltar = findViewById(R.id.botaoEditarId);
        Bundle extras = getIntent().getExtras();
        cpf = extras.get("cpf").toString();
        setarInfo(cpf);


        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfilUserActivity.this.finish();
            }
        });
    }


    public void setarInfo(String cpf){

        alunoDAO = new AlunoDAO(this);
        aluno = alunoDAO.retornaAluno(cpf);
        perfilNome.setText("Nome:\n" + aluno.getNome());
        perfilId.setText("Id:\n" + aluno.getId().toString()) ;
        perfilTelefone.setText("Telefone:\n" + aluno.getTelefone());


    }
}