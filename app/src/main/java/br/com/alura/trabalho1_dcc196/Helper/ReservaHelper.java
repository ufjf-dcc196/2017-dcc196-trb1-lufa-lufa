package br.com.alura.trabalho1_dcc196.Helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Livro;
import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.Model.Reserva;
import br.com.alura.trabalho1_dcc196.View.MainActivity;

/**
 * Created by Lucas on 24/11/2017.
 */

public class ReservaHelper {

    private SQLiteDatabase db;

    public ReservaHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaReserva();
    }

    private void criaTabelaReserva() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS reserva (id INTEGER PRIMARY KEY AUTOINCREMENT, id_participante INTEGER, id_livro INTEGER)");
        }catch (Exception e){
            Log.e("Reserva", "Erro ao criar a tabela!");
            Log.e("Reserva", e.getMessage());
        }
    }

    public void criarReserva(Participante p, Livro l){
        try{
            db.execSQL("INSERT INTO reserva (id_participante, id_livro) VALUES ('" +
                    MainActivity.ph.retornaIDParticipante(p)+"', '" +
                    MainActivity.lh.retornaIDLivro(l)+"')");
            //db.execSQL(String.format("INSERT INTO reserva (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Reserva", "Erro ao inserir um reserva");
            Log.e("Reserva", e.getLocalizedMessage());
        };
    }

    public List<Participante> listarTodasReservasLivro(Livro l) {
        Cursor resultado = db.rawQuery("SELECT id_livro, nome, email FROM reserva r INNER JOIN participante p ON r.id_participante=p.id", null);
        List<Participante> reservas = new ArrayList<>();
        resultado.moveToPosition(-1);
        int idLivro = MainActivity.lh.retornaIDLivro(l);
        while (resultado.moveToNext()){
            Integer id_livro = resultado.getInt(0);
            if(id_livro != 0 && idLivro == id_livro ){
                Participante u = new Participante();
                u.setNome(resultado.getString(1));
                u.setEmail(resultado.getString(2));
                reservas.add(u);
            }
        }
        return reservas;
    }

    public List<Livro> listarTodasReservasParticipante(Participante p) {
        Cursor resultado = db.rawQuery("SELECT id_participante, titulo, editora, ano FROM reserva r INNER JOIN livro l ON r.id_participante=l.id", null);
        List<Livro> reservas = new ArrayList<>();
        resultado.moveToPosition(-1);
        int idParticipante = MainActivity.ph.retornaIDParticipante(p);
        while (resultado.moveToNext()){
            Integer id_participante = resultado.getInt(0);
            if(id_participante != 0 && idParticipante == id_participante ){
                Livro u = new Livro();
                u.setTitulo(resultado.getString(1));
                u.setEditora(resultado.getString(2));
                u.setAno(resultado.getInt(3));
                reservas.add(u);
            }
        }
        return reservas;
    }

    public Integer retornaIDReserva(Reserva u){
        Cursor resultado = db.rawQuery("SELECT id_participante, id_livro FROM reserva", null);
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            if(resultado.getInt(1) == (u.getP()) && (resultado.getInt(2) == (u.getL())))
                return resultado.getInt(0);
        }
        return -1;
    }
}
