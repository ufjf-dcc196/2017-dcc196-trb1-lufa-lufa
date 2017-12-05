package br.com.alura.trabalho1_dcc196.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Helper.ParticipanteHelper;
import br.com.alura.trabalho1_dcc196.Model.Livro;
import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.R;

public class ActivityListaLivro extends AppCompatActivity {

    private Button btnVoltar;
    private TextView txtTitulo;
    private TextView txtEditora;
    private TextView txtAno;

    private ListView lstParticipantesReservados;
    private List<Participante> reservas = new ArrayList<>();
    private List<String> reservados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livro);
        Livro livro = (Livro) getIntent().getSerializableExtra("livro");

        lstParticipantesReservados = (ListView) findViewById(R.id.lstReservados);
        reservas = MainActivity.rh.listarTodasReservasLivro(livro);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                reservados
        );
        lstParticipantesReservados.setAdapter(adaptador);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo2);
        txtEditora = (TextView) findViewById(R.id.txtEditora2);
        txtAno = (TextView) findViewById(R.id.txtAnoPublicacao2);

        txtTitulo.setText(livro.getTitulo());
        txtEditora.setText(livro.getEditora());
        txtAno.setText(livro.getAno().toString());

        for(int i=0; i < MainActivity.getParticipantes().size(); i++) {
                Participante aux = MainActivity.getParticipantes().get(i);
                for(int j=0; j<MainActivity.rh.listarTodasReservasParticipante(aux).size(); j++) {
                    List<Livro> livrosReservados = MainActivity.rh.listarTodasReservasParticipante(aux);
                    if (livrosReservados.get(j).getTitulo().equals(livro.getTitulo())) {
                        adaptador.add(reservas.get(i).getNome().toString());
                    }
                }
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
