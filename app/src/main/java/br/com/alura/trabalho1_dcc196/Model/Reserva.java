package br.com.alura.trabalho1_dcc196.Model;

/**
 * Created by Lucas on 26/11/2017.
 */

public class Reserva {

    private Integer participanteID;
    private Integer livroID;

    public Reserva() { }

    public Reserva(Integer participanteID, Integer livroID)
    {
        this.participanteID = participanteID;
        this.livroID = livroID;
    }

    public Integer getParticipanteID() {
        return participanteID;
    }

    public void setParticipanteID(Integer participanteID) {
        this.participanteID = participanteID;
    }

    public Integer getLivroID() {
        return livroID;
    }

    public void setLivroID(Integer livroID) {
        this.livroID = livroID;
    }
}
