package br.com.alura.trabalho1_dcc196.Helper;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.alura.trabalho1_dcc196.Model.Participante;
import br.com.alura.trabalho1_dcc196.View.MainActivity;

/**
 * Created by Filipe on 22/10/2017.
 */

public class ParticipanteHelper {

    private SQLiteDatabase db = DbHelper.getInstance();

    public UsuarioHelper(SQLiteDatabase db) {
        this.db = db;
        criaTabelaUsuario();
    }

    public static String mostraHoraInicial(Participante p) {
        return p.getHrInicial().get(Calendar.HOUR)+":"+p.getHrInicial().get(Calendar.MINUTE)+":"+p.getHrInicial().get(Calendar.SECOND);
    }

    public static String mostraHoraFinal(Participante p) {
        return p.getHrFinal().get(Calendar.HOUR)+":"+p.getHrFinal().get(Calendar.MINUTE)+":"+p.getHrFinal().get(Calendar.SECOND);
    }
}
