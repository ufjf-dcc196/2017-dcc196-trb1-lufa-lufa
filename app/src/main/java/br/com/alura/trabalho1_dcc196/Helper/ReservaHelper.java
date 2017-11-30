package br.com.alura.trabalho1_dcc196.Helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Livro;
import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.Model.Reserva;


/**
 * Created by Lucas on 24/11/2017.
 */

public class ReservaHelper {
    
    private static SQLiteDatabase db = DbHelper.getInstance();

    public ReservaHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaReserva();
    }

    private void criaTabelaReserva() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS reserva (id INTEGER PRIMARY KEY AUTOINCREMENT, participanteID INTEGER, livroID INTEGER)");
        }catch (Exception e){
            Log.e("Reserva", "Erro ao criar a tabela!");
            Log.e("Reserva", e.getMessage());
        }
    }

    public void criarReserva(Participante p, Livro l){
        try{
            db.execSQL("INSERT INTO reserva (participanteID, livroID) VALUES ('" +
                    ParticipanteHelper.retornaIDParticipante(p)+"', '" +
                    LivroHelper.retornaIDLivro(l)+"')");
            //db.execSQL(String.format("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Reserva", "Erro ao inserir um reserva");
            Log.e("Reserva", e.getLocalizedMessage());
        };
    }

    public List<Reserva> listarTodos() {


        Cursor resultado = db.rawQuery("SELECT participanteID, livroID FROM reserva", null);
        List<Reserva> reservas = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            Reserva u = new Reserva();
            u.setParticipanteID(resultado.getInt(0));
            u.setLivroID(resultado.getInt(1));
            reservas.add(u);
        }
        return reservas;
    }

    public static Integer retornaIDReserva(Reserva u){
        Cursor resultado = db.rawQuery("SELECT participanteID, livroID FROM reserva", null);
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            if(resultado.getString(1).equals(u.getParticipanteID()) && resultado.getString(2).equals(u.getLivroID()))
                return resultado.getInt(0);
        }
        return -1;
    }
}
