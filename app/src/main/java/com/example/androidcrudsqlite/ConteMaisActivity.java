package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ConteMaisActivity extends AppCompatActivity {

    private TextView saudacao;
    private EditText descricao;
    private String nomeProblema;
    private String comunicacao;
    private Button avancar;
    private Spinner spinner;
    private Problema problema;
    private ProblemaDAO problemaDAO;
    private String valorSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conte_mais);
        spinner = findViewById(R.id.spinner);
        avancar = findViewById(R.id.botaoAvancar);
        saudacao = findViewById(R.id.saudacaoId);
        descricao = findViewById(R.id.descricaoId);
        descricao.setMovementMethod(new ScrollingMovementMethod());
        Bundle extras = getIntent().getExtras();
        comunicacao = extras.get("comunicacao").toString();
        problemaDAO = new ProblemaDAO(this);


        String[] palavras = comunicacao.split(" ");
        String cpfAluno = palavras[0];
        nomeProblema = palavras[1];

        saudacao.setText("Conte mais sobre seu problema com " + nomeProblema);


        ///spinner


        String[] opcoes = {"URGENTE!", "NOS PRÓXIMOS 15 DIAS", "NOS PRÓXIMOS 30 DIAS", " NOS PRÓXIMOS 3 MESES"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenha o valor selecionado pelo usuário
                valorSelecionado = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada foi selecionado
            }
        });
        ///fim do spinner - tipo / textoDescricao/prazo






        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String descricaoString = descricao.getText().toString().trim();
                if(descricaoString.isEmpty()) {
                    descricao.setError("Campo em branco");
                }else{

                    //montarProblema
                    Problema problema = new Problema();
                    problema.setCpfAluno(cpfAluno);
                    problema.setTipo(nomeProblema);
                    problema.setPrazo(valorSelecionado);
                    problema.setTextoDescricao(descricaoString);
                    problemaDAO.inserir(problema);
                    Toast.makeText(ConteMaisActivity.this, "Problema Adicionado ao Banco de Dados", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(new Intent(ConteMaisActivity.this, RegistradoActivity.class));
                    startActivity(intent);

                }



            }
        });







    }
}