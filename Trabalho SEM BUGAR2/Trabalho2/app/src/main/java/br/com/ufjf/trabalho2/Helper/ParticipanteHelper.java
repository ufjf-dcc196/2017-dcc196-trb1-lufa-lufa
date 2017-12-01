package br.com.ufjf.trabalho2.Helper;

/**
 * Created by Lucas on 30/11/2017.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.ufjf.trabalho2.Model.Participante;

public class ParticipanteHelper {

    private SQLiteDatabase db;

    public ParticipanteHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaParticipante();
    }

    private void criaTabelaParticipante() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS participante (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, email VARCHAR, hrInicial TIMESTAMP, hrFinal TIMESTAMP)");
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

    public void alterarParticipante(Participante u){
        try{
            String x = "";
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
            long hrInicial = u.getHrInicial().getTimeInMillis()*1000;
            if(u.getHrFinal() != null){
                 String hrFinal = formatter.format(u.getHrFinal().getTime());
                 x = "UPDATE participante SET hrFinal = CURRENT_TIMESTAMP WHERE id = '" + retornaIDParticipante(u) +"'" ;
            }else{
                x = "UPDATE participante SET hrInicial = CURRENT_TIMESTAMP WHERE id = '" + retornaIDParticipante(u) +"'" ;
            }
            db.execSQL(x);
        }catch(Exception e){
            Log.e("Participante", "Erro ao alterar um participante");
            Log.e("Participante", e.getLocalizedMessage());
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
            Long t = resultado.getLong(2) * 1000;
            if(t != 0){
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(t);
                u.setHrInicial(c);
            }
            Long t2 = resultado.getLong(3) * 1000;
            if(t2 != 0) {
                Calendar c2 = Calendar.getInstance();
                c2.setTimeInMillis(t2);
                u.setHrFinal(c2);
            }
            participantes.add(u);
        }
        return participantes;
    }

    public List<Participante> listarTodosEvento() {
        Cursor resultado = db.rawQuery("SELECT nome, email, hrInicial, hrFinal FROM participante", null);
        List<Participante> participantes = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            if(resultado.getLong(3) != 0) {
                Participante u = new Participante();
                u.setNome(resultado.getString(0));
                u.setEmail(resultado.getString(1));
                Long t = resultado.getLong(2) * 1000;
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(t);
                u.setHrInicial(c);
                participantes.add(u);
            }
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
