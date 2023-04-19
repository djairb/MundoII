package com.example.androidcrudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListarProblemasActivity extends AppCompatActivity {

    private List<Problema> problemasTodos;
    private ProblemaDAO problemaDAO;
    private ProfissionalDAO profissionalDAO;
    private Profissional profissional;
    private String cpf;
    private TextView profissonalNome;
    private ListView problemasLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_problemas);

        problemasLista = findViewById(R.id.listaProblemasId);
        profissonalNome = findViewById(R.id.nomeProfissionalId);
        Bundle extras = getIntent().getExtras();
        cpf = extras.get("cpf").toString();
        profissionalDAO = new ProfissionalDAO(this);
        profissional = profissionalDAO.retornaProfissional(cpf);
        profissonalNome.setText("Bem-vindo(a) ao sistema, profissional: " + profissional.getNome().toString());
        problemaDAO = new ProblemaDAO(this);
        encherLista();

        problemasLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Problema problemaSelecionado = problemasTodos.get(position);
                Toast.makeText(ListarProblemasActivity.this, problemaSelecionado.getId().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(new Intent(ListarProblemasActivity.this, ListaContatoProblema.class));
                intent.putExtra("id",problemaSelecionado.getId().toString());
                startActivity(intent);
            }
        });



    }




    public void encherLista(){

        problemasTodos = problemaDAO.obterTodos();
        ArrayAdapter<Problema> adapter = new ArrayAdapter<Problema>(this, android.R.layout.simple_list_item_1, problemasTodos);
        problemasLista.setAdapter(adapter);

    };
}