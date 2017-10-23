package br.com.alura.trabalho1_dcc196.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.trabalho1_dcc196.R;

public class ActivityListaLivro extends AppCompatActivity {

    private ListView lstLivros;

    private List<String> livros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livro);

        lstLivros = (ListView) findViewById(R.id.lstLivros);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                livros
        );
        lstLivros.setAdapter(adaptador);

        lstLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String escolha = adaptador.getItem(position);
                if (escolha != null) {

                    adaptador.remove(escolha);
                    return true;
                }
                return false;
            }
        });
    }
}
