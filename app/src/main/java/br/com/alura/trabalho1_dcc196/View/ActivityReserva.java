package br.com.alura.trabalho1_dcc196.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.R;

public class ActivityReserva extends AppCompatActivity {

    private TextView txtAux;
    private Button btnVoltar;
    private EditText txtParticipante;
    private EditText txtLivro;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        txtAux = (TextView) findViewById(R.id.txtAux);
        txtLivro = (EditText) findViewById(R.id.txtNomeLivro);
        txtParticipante = (EditText) findViewById(R.id.txtNomeParticipante);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String participante = txtParticipante.getText().toString();
                String livro = txtLivro.getText().toString();
                boolean achou = false;

                if(!participante.isEmpty() && !livro.isEmpty()){
                    for(int i=0; i< MainActivity.getParticipantes().size(); i++) {
                        if(MainActivity.getParticipantes().get(i).getNome().equals(participante) && MainActivity.getLivros().get(i).getTitulo().equals(livro)) {
                            MainActivity.getParticipantes().get(i).setNomeLivro(livro);
                            Toast.makeText(ActivityReserva.this, "Reserva feita com sucesso!", Toast.LENGTH_SHORT).show();
                            achou = true;
                        }
                    }

                    if(!achou) {
                        Toast.makeText(ActivityReserva.this, "Participante ou Livro invalido!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ActivityReserva.this, "Digite todas as informações do participante.", Toast.LENGTH_SHORT).show();
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
}
