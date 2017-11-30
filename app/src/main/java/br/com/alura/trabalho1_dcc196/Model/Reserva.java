package br.com.alura.trabalho1_dcc196.Model;

/**
 * Created by Lucas on 26/11/2017.
 */

public class Reserva {

    private Integer idParticipante;
    private Integer idLivro;

    public Reserva(Integer p, Integer l) {
        this.idParticipante = p;
        this.idLivro = l;
    }

    public Reserva() {
    }

    public Integer getP() {
        return idParticipante;
    }

    public void setP(Integer p) {
        this.idParticipante = p;
    }

    public Integer getL() {
        return idLivro;
    }

    public void setL(Integer l) {
        this.idLivro = l;
    }
}
