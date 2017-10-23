package br.com.alura.trabalho1_dcc196.Model;

import java.io.Serializable;
/**
 * Created by Filipe on 22/10/2017.
 */

public class Livro implements Serializable{

    private String titulo;
    private String editora;
    private Integer ano;

    public Livro(String titulo, String editora, Integer ano) {
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
    }

    public Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
