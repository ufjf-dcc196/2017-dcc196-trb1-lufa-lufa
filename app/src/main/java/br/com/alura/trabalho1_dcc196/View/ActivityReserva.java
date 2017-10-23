package br.com.alura.trabalho1_dcc196.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.alura.trabalho1_dcc196.R;

public class ActivityReserva extends AppCompatActivity {

    private TextView txtAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        txtAux = (TextView) findViewById(R.id.txtAux);
        String mensagem = getIntent().getStringExtra("mensagem");
        txtAux.setText(mensagem);
    }
}
