package br.com.alura.trabalho1_dcc196.Helper;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.View.MainActivity;

/**
 * Created by Filipe on 22/10/2017.
 */

public class ParticipanteHelper {

    private SQLiteDatabase db = DbHelper.getInstance();

    public ParticipanteHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaParticipante();
    }

    private void criaTabelaParticipante() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS participante (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, email VARCHAR, hrInicial DATETIME, hrFinal DATETIME)");
        }catch (Exception e){
            Log.e("Participante", "Erro ao criar a tabela!");
            Log.e("Participante", e.getMessage());
        }
    }

    public void criarParticipante(Participante u){
        try{
            db.execSQL("INSERT INTO participante (nome, email, hrInicial, hrFinal) VALUES ('" +
                    u.getNome()+"', '" +
                    u.getEmail()+"', '" +
                    u.getHrInicial()+"', '" +
                    u.getHrFinal()+"')");
            //db.execSQL(String.format("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Participante", "Erro ao inserir um participante");
            Log.e("Participante", e.getLocalizedMessage());
        };
    }

    public void criarMantenedor(Participante u){
        try{
            db.execSQL("INSERT INTO participante (nome, rg, cpf, email, endereco, senha, user) VALUES ('" +
                   u.getNome()+"', '" +
                   u.getEmail()+"', '" +
                   u.getHrInicial()+"', '" +
                   u.getHrFinal()+"')");
            //db.execSQL(String.format("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Mantenedor", "Erro ao inserir um mantenedor");
            Log.e("Mantenedor", e.getLocalizedMessage());
        };
    }

    public List<Participante> listarTodos() {
        Cursor resultado = db.rawQuery("SELECT nome, email, hrInicial, hrFinal FROM participante", null);
        List<Participante> participantes = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            Participante u = new Participante();
            u.setNome(resultado.getString(0));
            u.setEmail(resultado.getString(1));
            u.setHrInicial(resultado.getString(2));
            u.setHrFinal(resultado.getString(3));
            participantes.add(u);
        }
        return participantes;
    }

    public Integer retornaIDParticipante(Participante u){
        Cursor resultado = db.rawQuery("SELECT id, nome, email FROM participante", null);
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            if(resultado.getString(1).equals(u.getNome()) && resultado.getString(2).equals(u.getEmail()))
                return resultado.getInt(0);
        }
        return -1;
    }


    
    public static String mostraHoraInicial(Participante p) {
        return p.getHrInicial().get(Calendar.HOUR)+":"+p.getHrInicial().get(Calendar.MINUTE)+":"+p.getHrInicial().get(Calendar.SECOND);
    }

    public static String mostraHoraFinal(Participante p) {
        return p.getHrFinal().get(Calendar.HOUR)+":"+p.getHrFinal().get(Calendar.MINUTE)+":"+p.getHrFinal().get(Calendar.SECOND);
    }
}
