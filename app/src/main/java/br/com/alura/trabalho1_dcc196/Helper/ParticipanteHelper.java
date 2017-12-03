package br.com.alura.trabalho1_dcc196.Helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.View.MainActivity;

/**
 * Created by Filipe on 22/10/2017.
 */

public class ParticipanteHelper {

    private SQLiteDatabase db;

    public ParticipanteHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaParticipante();
    }

    private void criaTabelaParticipante() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS participante (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, email VARCHAR, hrInicial VARCHAR, hrFinal VARCHAR)");
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
                    null+"', '" +
                    null+"')");

        }catch(Exception e){
            Log.e("Participante", "Erro ao inserir um participante");
            Log.e("Participante", e.getLocalizedMessage());
        };
    }

    public void alterarParticipante(Participante u){
        try{
            String sql = "";
            //DateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
            //long hrInicial = u.getHrInicial().getTimeInMillis()*1000;
            if(u.getHrFinal() != null){
                sql += "UPDATE participante SET hrFinal = '" + mostraHoraFinal(u) + "' WHERE id = '" + retornaIDParticipante(u) +"'" ;
            }else if(u.getHrInicial() != null){
                sql += "UPDATE participante SET hrInicial = '" + mostraHoraInicial(u) + "' WHERE id = '" + retornaIDParticipante(u) +"'" ;
            } else {
                sql += "UPDATE participante SET hrInicial = '', hrFinal = '' WHERE id = '" + retornaIDParticipante(u) +"'" ;
            }
            db.execSQL(sql);
        }catch(Exception e){
            Log.e("Participante", "Erro ao alterar um participante");
            Log.e("Participante", e.getLocalizedMessage());
        };
    }

    public List<Participante> listarTodos() {

        Cursor resultado = db.rawQuery("SELECT nome, email, hrInicial, hrFinal FROM participante", null);
        List<Participante> participantes = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()) {
            Participante u = new Participante();
            u.setNome(resultado.getString(0));
            u.setEmail(resultado.getString(1));
            u.setHr_inicial(resultado.getString(2));
            u.setHr_final(resultado.getString(3));
            participantes.add(u);
        }
        return participantes;
    }

    public List<Participante> listarTodosEvento() {
        Cursor resultado = db.rawQuery("SELECT nome, email, hrInicial, hrFinal FROM participante", null);
        List<Participante> participantes = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            String hi = resultado.getString(2);
            String hf = resultado.getString(3);
            if(hf.equalsIgnoreCase("") && !hi.equalsIgnoreCase("")) {
                Participante u = new Participante();
                u.setNome(resultado.getString(0));
                u.setEmail(resultado.getString(1));
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
