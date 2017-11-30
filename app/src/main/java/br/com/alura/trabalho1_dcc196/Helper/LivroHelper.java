package br.com.alura.trabalho1_dcc196.Helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Livro;

/**
 * Created by Lucas on 24/11/2017.
 */

public class LivroHelper {

    private static SQLiteDatabase db = DbHelper.getInstance();

    public LivroHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaLivro();
    }

    private void criaTabelaLivro() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS livro (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, editora VARCHAR, anor INTEGER)");
        }catch (Exception e){
            Log.e("Livro", "Erro ao criar a tabela!");
            Log.e("Livro", e.getMessage());
        }
    }

    public void criarLivro(Livro u){
        try{
            db.execSQL("INSERT INTO livro (titulo, editora, ano) VALUES ('" +
                    u.getTitulo()+"', '" +
                    u.getEditora()+"', '" +
                    u.getAno()+"')");
            //db.execSQL(String.format("INSERT INTO livro (titulo, autor, editora, ano, preco) VALUES ('%s','%s','%s',%d,%f)", l.getTitulo(), l.getAutor(), l.getEditora(), l.getAno(), l.getPreco()));

        }catch(Exception e){
            Log.e("Livro", "Erro ao inserir um livro");
            Log.e("Livro", e.getLocalizedMessage());
        };
    }

    public List<Livro> listarTodos() {


        Cursor resultado = db.rawQuery("SELECT titulo, editora, ano FROM livro", null);
        List<Livro> livros = new ArrayList<>();
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            Livro u = new Livro();
            u.setTitulo(resultado.getString(0));
            u.setEditora(resultado.getString(1));
            u.setAno(resultado.getInt(2));
            livros.add(u);
        }
        return livros;
    }

    public static Integer retornaIDLivro(Livro u){
        Cursor resultado = db.rawQuery("SELECT id, titulo, editora FROM livro", null);
        resultado.moveToPosition(-1);
        while (resultado.moveToNext()){
            if(resultado.getString(1).equals(u.getTitulo()) && resultado.getString(2).equals(u.getEditora()))
                return resultado.getInt(0);
        }
        return -1;
    }
}
