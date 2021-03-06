package br.com.alura.trabalho1_dcc196.View;

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

import java.util.ArrayList;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Helper.ParticipanteHelper;
import br.com.alura.trabalho1_dcc196.Model.Livro;
import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.R;

public class ActivityReserva extends AppCompatActivity {


    private Button btnVoltar;
    private Button btnSalvar;
    private ListView lstParticipantes;
    private ListView lstLivros;
    private List<Livro> livros;
    private List<Participante> participantes = new ArrayList<>();

    private Participante participanteSelecionado = null;
    private Livro livroSelecionado = null;

    ArrayAdapter<Participante> adaptadorParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        livros = MainActivity.lh.listarTodos();
        //participantes = MainActivity.getParticipantesNoEvento();
        participantes = MainActivity.ph.listarTodosEvento();

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        lstParticipantes = (ListView) findViewById(R.id.lstParticipantes);
        lstLivros = (ListView) findViewById(R.id.lstLivros);

        adaptadorParticipante = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                participantes
        );
        lstParticipantes.setAdapter(adaptadorParticipante);

        final ArrayAdapter<Livro> adaptadorLivro = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                livros
        );
        lstLivros.setAdapter(adaptadorLivro);

        lstParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                  participanteSelecionado = (Participante) adapter.getItemAtPosition(position);
              }
        });

        lstLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                livroSelecionado = (Livro) adapter.getItemAtPosition(position);
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(livroSelecionado != null && participanteSelecionado != null) {
                    for (int i = 0; i < participantes.size(); i++) {
                        if (participantes.get(i).getNome().equals(participanteSelecionado.getNome())) {
                            participantes.get(i).adicionaReserva(livroSelecionado);
                            MainActivity.rh.criarReserva(participanteSelecionado,livroSelecionado);
                            Toast.makeText(ActivityReserva.this, "Reserva feita com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                } else {
                    Toast.makeText(ActivityReserva.this, "Selecione um Participante ou Livro valido!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (adaptadorParticipante != null) {
//            adaptadorParticipante.clear();
//            adaptadorParticipante.addAll(MainActivity.ph.listarTodosEvento());
//        }
//    }
}
