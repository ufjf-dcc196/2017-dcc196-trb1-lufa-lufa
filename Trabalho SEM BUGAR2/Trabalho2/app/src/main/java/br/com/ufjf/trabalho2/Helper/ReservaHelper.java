package br.com.ufjf.trabalho2.Helper;

/**
 * Created by Lucas on 30/11/2017.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.ufjf.trabalho2.Model.Livro;
import br.com.ufjf.trabalho2.Model.Participante;
import br.com.ufjf.trabalho2.Model.Reserva;
import br.com.ufjf.trabalho2.View.MainActivity;

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

//    public List<Reserva> listarTodos() {
//        Cursor resultado = db.rawQuery("SELECT titulo, editora, ano FROM reserva", null);
//        List<Reserva> reservas = new ArrayList<>();
//        resultado.moveToPosition(-1);
//        while (resultado.moveToNext()){
//            Reserva u = new Reserva();
//            u.setTitulo(resultado.getString(0));
//            u.setEditora(resultado.getString(1));
//            u.setAno(resultado.getInt(2));
//            reservas.add(u);
//        }
//        return reservas;
//    }

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
