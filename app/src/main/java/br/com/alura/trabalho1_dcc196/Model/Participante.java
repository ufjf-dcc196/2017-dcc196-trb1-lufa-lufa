package br.com.alura.trabalho1_dcc196.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Filipe on 22/10/2017.
 */

public class Participante implements Serializable {
    private String nome;
    private String email;
    private Calendar hrInicial;
    private String hr_inicial;
    private Calendar hrFinal;
    private String hr_final;
    private ArrayList<Livro> livrosReservados = new ArrayList<Livro>();;

    public Participante() {
    }

    public Participante(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.hrInicial = null;
        this.hrFinal = null;
        this.hr_inicial = "";
        this.hr_final = "";
    }

    public Calendar getHrFinal() {
        return hrFinal;
    }

    public void setHrFinal(Calendar hrFinal){
        this.hrFinal = hrFinal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getHrInicial() {
        return hrInicial;
    }

    public void setHrInicial(Calendar hrInicial) {
        this.hrInicial = hrInicial;
    }

    public ArrayList<Livro> getLivrosReservados() {
        return livrosReservados;
    }

    public void setLivrosReservados(ArrayList<Livro> livrosReservados) {
        this.livrosReservados = livrosReservados;
    }

    public String getHr_inicial() { return hr_inicial;}

    public void setHr_inicial(String hr_inicial) {
        this.hr_inicial = hr_inicial;
    }

    public String getHr_final() {
        return hr_final;
    }

    public void setHr_final(String hr_final) {
        this.hr_final = hr_final;
    }

    public void adicionaReserva (Livro l) {
        this.livrosReservados.add(l);
    }

    @Override
    public String toString() {
        return nome;
    }
};

