package br.com.alura.trabalho1_dcc196.View;

import android.view.View;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.R;

public class ActivityCadastroParticipante extends AppCompatActivity {

    private TextView txtAux;
    private Button btnSalvar;
    private Button btnVoltar;
    private EditText txtNome;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        txtAux = (TextView) findViewById(R.id.txtAux);
        btnSalvar = (Button) findViewById(R.id.btnCadastrarP);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        txtNome = (EditText) findViewById(R.id.txtNomeParticipante);
        txtEmail= (EditText) findViewById(R.id.txtEmail);

        String mensagem = getIntent().getStringExtra("mensagem");
        txtAux.setText(mensagem);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();

                if(!nome.isEmpty() && !email.isEmpty()){
                    Participante p = new Participante(nome,email);
                    MainActivity.getParticipantes().add(p);
                    Toast.makeText(ActivityCadastroParticipante.this, "Participante salvo com sucesso!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ActivityCadastroParticipante.this, "Digite todas as informações do participante.", Toast.LENGTH_SHORT).show();

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
