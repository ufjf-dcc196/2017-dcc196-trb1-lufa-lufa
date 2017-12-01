package br.com.ufjf.trabalho2.View;

/**
 * Created by Lucas on 30/11/2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.ufjf.trabalho2.Helper.ParticipanteHelper;
import br.com.ufjf.trabalho2.Model.Participante;
import br.com.ufjf.trabalho2.R;
import br.com.ufjf.trabalho2.R;

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
        if(participante.getHrInicial() != null)
            txtHrEntrada.setText(ParticipanteHelper.mostraHoraInicial(participante));
        else txtHrEntrada.setText("Sem horário de entrada.");
        if(participante.getHrFinal() != null)
            txtHrSaida.setText(ParticipanteHelper.mostraHoraFinal(participante));
        else txtHrSaida.setText("Sem horário de saída.");

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
