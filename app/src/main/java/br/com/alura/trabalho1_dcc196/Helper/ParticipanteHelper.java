package br.com.alura.trabalho1_dcc196.Helper;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Participante;

/**
 * Created by Filipe on 22/10/2017.
 */

public class ParticipanteHelper {

    private List<Participante> participantes;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void update(ArrayAdapter arrayAdapter, ArrayList<Participante> participantes){
        arrayAdapter.clear();
        for (Participante p : participantes){
            arrayAdapter.add(p);
        }
    }

    public void addParticipante(Participante participante) {
        participantes.add(participante);
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }
    
    public static String mostraHoraInicial(Participante p) {
        return p.getHrInicial().get(Calendar.HOUR)+":"+p.getHrInicial().get(Calendar.MINUTE)+":"+p.getHrInicial().get(Calendar.SECOND);
    }

    public static String mostraHoraFinal(Participante p) {
        return p.getHrFinal().get(Calendar.HOUR)+":"+p.getHrFinal().get(Calendar.MINUTE)+":"+p.getHrFinal().get(Calendar.SECOND);
    }
}
