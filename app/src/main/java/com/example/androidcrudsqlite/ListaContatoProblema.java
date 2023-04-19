package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListaContatoProblema extends AppCompatActivity {

    private TextView nomeAluno;
    private TextView tipoProblema;
    private TextView prazoProblema;
    private TextView descricaoProblema;
    private Button botaoProblema;
    private ProblemaDAO problemaDAO;
    private AlunoDAO alunoDAO;
    private Problema problema;
    private Aluno aluno;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contato_problema);

        nomeAluno = findViewById(R.id.userNameId);
        tipoProblema = findViewById(R.id.tipoProblemaId);
        prazoProblema = findViewById(R.id.prazoId);
        descricaoProblema = findViewById(R.id.textoDescricao);
        botaoProblema = findViewById(R.id.botaoConversa);
        Bundle extras = getIntent().getExtras();
        id = extras.get("id").toString();
        problemaDAO = new ProblemaDAO(this);
        problema = problemaDAO.retornaProblema(Integer.parseInt(id));
        alunoDAO = new AlunoDAO(this);
        aluno = alunoDAO.retornaAluno(problema.getCpfAluno());
        nomeAluno.setText(aluno.getNome());
        tipoProblema.setText(problema.getTipo());
        prazoProblema.setText(problema.getPrazo());
        descricaoProblema.setText(problema.getTextoDescricao());
        botaoProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "5511";
                String numeroAluno = aluno.getTelefone();
                number = number+numeroAluno;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/" + number));
                startActivity(intent);
            }
        });


    }
}