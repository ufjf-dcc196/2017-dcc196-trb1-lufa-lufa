package br.com.alura.trabalho1_dcc196.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.alura.trabalho1_dcc196.Helper.ParticipanteHelper;
import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.R;

public class ActivityParticipante extends AppCompatActivity {

    private Button btnVoltar;
    private TextView txtNome;
    private TextView txtEmail;
    private TextView txtHrEntrada;
    private TextView txtHrSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participante);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        txtNome = (TextView) findViewById(R.id.txtNomeParticipante);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtHrEntrada = (TextView) findViewById(R.id.txtHrEntrada);
        txtHrSaida = (TextView) findViewById(R.id.txtHrSaida);

        Participante participante = (Participante) getIntent().getSerializableExtra("participante");
        txtNome.setText(participante.getNome());
        txtEmail.setText(participante.getEmail());
        if(participante.getHr_inicial() != "")
            txtHrEntrada.setText(participante.getHr_inicial());
        else txtHrEntrada.setText("Sem horário de entrada.");
        if(participante.getHr_final() != "")
            txtHrSaida.setText(participante.getHr_final());
        else txtHrSaida.setText("Sem horário de saída.");

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
