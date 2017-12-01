package br.com.ufjf.trabalho2.Model;

/**
 * Created by Lucas on 30/11/2017.
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
