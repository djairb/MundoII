package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class CadastroProfissionalActivity extends AppCompatActivity implements Serializable {

    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText funcao;
    private EditText senhaRegistro;
    private EditText confirmarSenha;
    private Button salvar;
    private ProfissionalDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional);

        nome = findViewById(R.id.editNomeCampo);
        cpf = findViewById(R.id.editCpfCampo);
        telefone = findViewById(R.id.editTelefoneCampo);
        funcao = findViewById(R.id.editFuncao);
        salvar = findViewById(R.id.botaoSalvar);
        senhaRegistro = findViewById(R.id.editSenhaCampo);
        confirmarSenha = findViewById(R.id.editConfirmaSenhaCampo);
        dao = new ProfissionalDAO(this);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dao.acabarTudo();
                if(validarCampos()){
                    montarProfissional();

                }

            }
        });


    }

    public void montarProfissional(){
        Profissional profissional = new Profissional();
        profissional.setCpf(cpf.getText().toString());
        profissional.setNome(nome.getText().toString());
        profissional.setTelefone(telefone.getText().toString());
        profissional.setSenha(senhaRegistro.getText().toString());
        profissional.setFuncao(funcao.getText().toString());
        ///adicionar criptografia
        if(dao.existeCpf(profissional.getCpf())){
            Toast.makeText(this, "Cpf informado já cadastrado.", Toast.LENGTH_LONG).show();

        }else{
            Long id = dao.inserir(profissional);
            Toast.makeText(this, "Profissional inserido com id: ." + id, Toast.LENGTH_LONG).show();
            CadastroProfissionalActivity.this.finish();

        }

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
        String cpfString = cpf.getText().toString().trim();
        if(cpfString.isEmpty()){
            erro = true;
            cpf.setError("Campo em branco");
        }else if(cpfString.length() != 11){
            erro = true;
            cpf.setError("Cpf não em 11 dígitos");
        }else if(!cpfString.matches("[0-9]+")){
            erro = true;
            cpf.setError("Cpf não contem apenas numeros");
        }
        return erro;

    }

    private boolean validarNome(){

        boolean erro = false;
        String nomeString = nome.getText().toString().trim();
        if(nomeString.isEmpty()){
            erro = true;
            nome.setError("Campo em braco");
        }
        return erro;

    }

    private boolean validarTelefone(){

        boolean erro = false;
        String telefoneString = telefone.getText().toString().trim();
        if (telefoneString.isEmpty()){
            erro = true;
            telefone.setError("Campo em branco");
        } else if(!telefoneString.matches("[0-9]+")){
            erro = true;
            telefone.setError("Telefone não contem apenas numeros");
        }
        return erro;

    }

    private boolean validarSenha(){

        boolean erro = false;

        String senha = senhaRegistro.getText().toString();
        String senhaConfirm = confirmarSenha.getText().toString();
        if(senha.isEmpty() && senhaConfirm.isEmpty()){
            erro = true;
            senhaRegistro.setError("Campo em branco");
            confirmarSenha.setError("Campo em branco");
        }else if(senha.isEmpty()){
            erro = true;
            senhaRegistro.setError("Campo em branco");
        }else if(senha.length() < 6){
            erro = true;
            senhaRegistro.setError("A senha deve conter pelo menos 6 digitos");
        }else if(!senha.equals(senhaConfirm)){
            erro = true;
            senhaRegistro.setError("As senhas devem ser iguais");
            confirmarSenha.setError("As senhas devem ser iguais");

        }
        return erro;

    }
}