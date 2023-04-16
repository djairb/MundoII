package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kotlin.Suppress;

public class EditarPerfilActivity extends AppCompatActivity {


    private EditText nomeEditar;
    private EditText telefoneEditar;
    private EditText cpfEditar;
    private EditText senhaEditar;
    private EditText confirmaSenhaEditar;
    private Button botaoAtualizar;
    private Aluno aluno;
    private AlunoDAO alunoDAO;
    private String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        nomeEditar= findViewById(R.id.nomeEditarId);
        telefoneEditar = findViewById(R.id.telefoneEditarId);
        cpfEditar = findViewById(R.id.cpfEditarId);
        senhaEditar = findViewById(R.id.senhaEditarId);
        confirmaSenhaEditar = findViewById(R.id.confirmaSenhaEditarId);
        botaoAtualizar = findViewById(R.id.botaoAtualizarId);
        Bundle extras = getIntent().getExtras();
        cpf = extras.get("cpf").toString();
        setarInfo(cpf);
        alunoDAO = new AlunoDAO(this);

        botaoAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    atualizarAluno();
                }
            }
        });

    }
    @Override
    public void onBackPressed(){
        voltarTela();
    }

    public void setarInfo(String cpf){

        alunoDAO = new AlunoDAO(this);

        aluno = alunoDAO.retornaAluno(cpf);

        nomeEditar.setText(aluno.getNome().toString());
        telefoneEditar.setText(aluno.getTelefone().toString());
        cpfEditar.setText(aluno.getCpf().toString());
        senhaEditar.setText(aluno.getSenha().toString());
        confirmaSenhaEditar.setText(aluno.getSenha().toString());

    }

    public void atualizarAluno(){
        ///ta errado porque o id ta nulo cibola - no metodo de alunodao - passa o id do seu perfil pra ca

        aluno.setCpf(cpfEditar.getText().toString());
        aluno.setNome(nomeEditar.getText().toString());
        aluno.setTelefone(telefoneEditar.getText().toString());
        aluno.setSenha(senhaEditar.getText().toString());
        alunoDAO.atualizar(aluno);
        Toast.makeText(this, "Aluno atualizado", Toast.LENGTH_SHORT).show();
        voltarTela();
    }

    public void voltarTela(){
        Intent intent = new Intent(EditarPerfilActivity.this, SeuPerfilActivity.class);
        intent.putExtra("cpf",cpf);
        startActivity(intent);
        EditarPerfilActivity.this.finish();

    }


    private boolean validarCampos(){

        boolean erro = true;
        if (validarCpf()){
            erro = false;
        }
        if (validarNome()){
            erro = false;
        }
        if(validarTelefone()){
            erro = false;
        }
        if (validarSenha()){
            erro = false;
        }
        return erro;

    }

    private boolean validarCpf(){

        boolean erro = false;
        String cpfString = cpfEditar.getText().toString().trim();
        if(cpfString.isEmpty()){
            erro = true;
            cpfEditar.setError("Campo em branco");
        }else if(cpfString.length() != 11){
            erro = true;
            cpfEditar.setError("Cpf não em 11 dígitos");
        }else if(!cpfString.matches("[0-9]+")){
            erro = true;
            cpfEditar.setError("Cpf não contem apenas numeros");
        }
        return erro;

    }

    private boolean validarNome(){

        boolean erro = false;
        String nomeString = nomeEditar.getText().toString().trim();
        if(nomeString.isEmpty()){
            erro = true;
            nomeEditar.setError("Campo em braco");
        }
        return erro;

    }

    private boolean validarTelefone(){

        boolean erro = false;
        String telefoneString = telefoneEditar.getText().toString().trim();
        if (telefoneString.isEmpty()){
            erro = true;
            telefoneEditar.setError("Campo em branco");
        } else if(!telefoneString.matches("[0-9]+")){
            erro = true;
            telefoneEditar.setError("Telefone não contem apenas numeros");
        }
        return erro;

    }

    private boolean validarSenha(){

        boolean erro = false;

        String senha = senhaEditar.getText().toString();
        String senhaConfirm = confirmaSenhaEditar.getText().toString();
        if(senha.isEmpty() && senhaConfirm.isEmpty()){
            erro = true;
            senhaEditar.setError("Campo em branco");
            senhaEditar.setError("Campo em branco");
        }else if(senha.isEmpty()){
            erro = true;
            senhaEditar.setError("Campo em branco");
        }else if(senha.length() < 6){
            erro = true;
            senhaEditar.setError("A senha deve conter pelo menos 6 digitos");
        }else if(!senha.equals(senhaConfirm)){
            erro = true;
            senhaEditar.setError("As senhas devem ser iguais");
            confirmaSenhaEditar.setError("As senhas devem ser iguais");

        }
        return erro;

    }





}