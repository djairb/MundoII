package com.example.androidcrudsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SeuPerfilActivity extends AppCompatActivity {

    private TextView seuPerfilNome;
    private TextView seuPerfilId;
    private TextView seuPerfilTelefone;
    private TextView seuPerfilSenha;
    private Button botaoEditar;
    private Button botaoDeletar;
    private AlunoDAO alunoDAO;
    private Aluno aluno;
    private String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seu_perfil);

        seuPerfilNome = findViewById(R.id.seuPerfilNomeId);
        seuPerfilId = findViewById(R.id.seuPerfilIdid);
        seuPerfilTelefone = findViewById(R.id.seuPerfilTelefoneID);
        seuPerfilSenha = findViewById(R.id.seuPerfilSenhaId);
        botaoDeletar = findViewById(R.id.botaoDeletarId);
        botaoEditar = findViewById(R.id.botaoEditarId);
        Bundle extras = getIntent().getExtras();
        cpf = extras.get("cpf").toString();
        alunoDAO = new AlunoDAO(this);
        setarInfo(cpf);

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(SeuPerfilActivity.this, EditarPerfilActivity.class));
                intent.putExtra("cpf",cpf);
                startActivity(intent);
                SeuPerfilActivity.this.finish();
            }
        });

        botaoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog dialog = new AlertDialog.Builder(SeuPerfilActivity.this)
                        .setTitle("Atenção")
                        .setMessage("Deseja realmente excluir seu perfil?")
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletarAluno(cpf);
                            }
                        })
                        .show();
            }
        });
    }

    public void voltarTela(){
        Intent intent = new Intent(SeuPerfilActivity.this, ListarAlunosActivity.class);
        intent.putExtra("cpf",cpf);
        startActivity(intent);
        SeuPerfilActivity.this.finish();

    }


    @Override
    public void onBackPressed(){
        voltarTela();
    }

    public void setarInfo(String cpf){

        aluno = alunoDAO.retornaAluno(cpf);
        seuPerfilSenha.setText("Senha:\n" + aluno.getSenha());
        seuPerfilNome.setText("Nome:\n" + aluno.getNome());
        seuPerfilId.setText("Id:\n" + aluno.getId().toString()) ;
        seuPerfilTelefone.setText("Telefone:\n" + aluno.getTelefone());


    }

    public void deletarAluno(String cpf){
        alunoDAO.deletar(cpf);
        Toast.makeText(this, "Perfil deletado.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SeuPerfilActivity.this, LoginActivity.class);

        startActivity(intent);
        SeuPerfilActivity.this.finish();





    }


}