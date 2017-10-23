package br.com.alura.trabalho1_dcc196.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Helper.ParticipanteHelper;
import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.R;

public class MainActivity extends AppCompatActivity {

    private Button btnParticipante;
    private Button btnLivro;
    private Button btnReserva;
    private ListView lstParticipantes;

    private static List<Participante> participantes = new ArrayList<>();

    public static List<Participante> getParticipantes() {
        return participantes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLivro = (Button) findViewById(R.id.btnCadastroLivros);
        btnParticipante = (Button) findViewById(R.id.btnCadastroParticipante);
        btnReserva = (Button) findViewById(R.id.btnCadastroReserva);
        lstParticipantes = (ListView) findViewById(R.id.lstParticipantes);

        btnParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrarParticipante = new Intent(
                        MainActivity.this, ActivityCadastroParticipante.class
                );
                cadastrarParticipante.putExtra("mensagem", "Digite os dados do Participante");
                startActivity(cadastrarParticipante);
            }
        });

        btnLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastrarLivro = new Intent(
                        MainActivity.this, ActivityCadastroLivro.class
                );
                cadastrarLivro.putExtra("mensagem", "Digite os dados do Livro");
                startActivity(cadastrarLivro);
            }
        });

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reservarLivro = new Intent(
                        MainActivity.this, ActivityReserva.class
                );
                reservarLivro.putExtra("mensagem", "Escolha o Participante e o Livro para fazer a reserva");
                startActivity(reservarLivro);
            }
        });

        final ArrayAdapter<Participante> adaptador = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                participantes
        );
        lstParticipantes.setAdapter(adaptador);

        lstParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Participante escolha = adaptador.getItem(position);
                if (escolha.getHrInicial() == null) {
                    escolha.setHrInicial(Calendar.getInstance());
                    Toast.makeText(MainActivity.this,"Participante "+escolha.getNome()+" entrou às "+ParticipanteHelper.mostraHoraInicial(escolha)+".", Toast.LENGTH_SHORT).show();
                } else if (escolha.getHrFinal() == null){
                    escolha.setHrFinal(Calendar.getInstance());
                    Toast.makeText(MainActivity.this, "Participante "+escolha.getNome()+" saiu às "+ParticipanteHelper.mostraHoraFinal(escolha)+".", Toast.LENGTH_SHORT).show();
                } else {
                    escolha.setHrInicial(null);
                    escolha.setHrFinal(null);
                    Toast.makeText(MainActivity.this, "Participante "+escolha.getNome()+" reiniciou seu horário.", Toast.LENGTH_SHORT).show();
                }
                adaptador.notifyDataSetChanged();
                return true;
            }
        });



        lstParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Participante escolha = adaptador.getItem(position);
                Intent in = new Intent(MainActivity.this, ActivityParticipante.class);
                in.putExtra("participante", escolha);
                startActivity(in);
            }
        });

    }
}
