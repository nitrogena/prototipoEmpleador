package org.jboard.prototipo.vacantes;

import android.content.Context;

import java.util.ArrayList;

import org.jboard.prototipo.Adaptadores.VacanteAdapter;
import org.jboard.prototipo.BD.InteractorVacante;
import org.jboard.prototipo.Datos.Vacantes;

/**
 * Created by USUARIO on 14/02/2017.
 */

public class VacantePresenter implements InterfaceVacantePresenter{

    private InterfaceVacante interfazVacante;
    private Context contexto;
    private InteractorVacante interactorVacante;
    private ArrayList<Vacantes> arrLstVacantes;

    public VacantePresenter(InterfaceVacante interfazVacante, Context contexto){
        this.interfazVacante = interfazVacante;
        this.contexto = contexto;
        obtenerVacantesBD();
    }

    @Override
    public void obtenerVacantesBD() {
        interactorVacante = new InteractorVacante(contexto);
        arrLstVacantes = interactorVacante.obtenerDatos();
        mostrarVacantes();
    }

    @Override
    public void mostrarVacantes() {
        interfazVacante.inicializarAdaptador(interfazVacante.generarAdapter(arrLstVacantes));
        interfazVacante.generarLlv();
    }
}
