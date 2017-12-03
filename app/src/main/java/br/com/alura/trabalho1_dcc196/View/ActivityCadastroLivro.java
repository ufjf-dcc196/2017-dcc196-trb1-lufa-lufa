package br.com.alura.trabalho1_dcc196.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Livro;
import br.com.alura.trabalho1_dcc196.R;

public class ActivityCadastroLivro extends AppCompatActivity {

    private TextView txtAux;
    private Button btnSalvar;
    private Button btnVoltar;
    private EditText txtTitulo;
    private EditText txtEditora;
    private EditText txtAno;
    private ListView lstLivros;
    private static List<Livro> livros = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        txtAux = (TextView) findViewById(R.id.txtAux);
        btnSalvar = (Button) findViewById(R.id.btnCadastrarL);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtEditora= (EditText) findViewById(R.id.txtEditora);
        txtAno= (EditText) findViewById(R.id.txtAno);
        lstLivros = (ListView) findViewById(R.id.lstLivros);

        String mensagem = getIntent().getStringExtra("mensagem");
        txtAux.setText(mensagem);

        livros = MainActivity.lh.listarTodos();

        final ArrayAdapter<Livro> adaptador = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                livros
        );

        lstLivros.setAdapter(adaptador);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titulo = txtTitulo.getText().toString();
                String editora = txtEditora.getText().toString();
                Integer ano = 0;
                try {
                    ano = Integer.valueOf(txtAno.getText().toString());
                    if(!titulo.isEmpty() || !editora.isEmpty() || ano!=0){
                        Livro l = new Livro(titulo,editora,ano);
                        MainActivity.getLivros().add(l);
                        adaptador.add(l);
                        MainActivity.lh.criarLivro(l);
                        Toast.makeText(ActivityCadastroLivro.this, "Livro salvo com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(ActivityCadastroLivro.this, "Digite todas as informações do livro.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(ActivityCadastroLivro.this, "Valor de ano inválido. Digite novamente.", Toast.LENGTH_SHORT).show();
                    txtAno.requestFocus();
                }
                adaptador.notifyDataSetChanged();
            }
        });

        lstLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro escolha = adaptador.getItem(position);
                Intent in = new Intent(ActivityCadastroLivro.this, ActivityListaLivro.class);
                in.putExtra("livro", escolha);
                startActivity(in);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
