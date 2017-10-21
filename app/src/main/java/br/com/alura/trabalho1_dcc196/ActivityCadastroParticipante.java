package br.com.alura.trabalho1_dcc196;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityCadastroParticipante extends AppCompatActivity {

    private TextView txtAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);
        txtAux = (TextView) findViewById(R.id.textAux);
        String mensagem = getIntent().getStringExtra("mensagem");
        txtAux.setText(mensagem);
    }
}
