package org.jboard.prototipo.vacantes;

import org.jboard.prototipo.Adaptadores.VacanteAdapter;
import org.jboard.prototipo.Datos.Vacantes;

import java.util.ArrayList;

/**
 * Created by USUARIO on 14/02/2017.
 */

public interface InterfaceVacante {
    public void generarLlv();

    public VacanteAdapter generarAdapter(ArrayList<Vacantes> arrLstVacantes);

    public void inicializarAdaptador(VacanteAdapter vaVacante);

}
