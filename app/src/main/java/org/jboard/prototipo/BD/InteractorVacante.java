package org.jboard.prototipo.BD;

import android.content.ContentValues;
import android.content.Context;

import org.jboard.prototipo.Datos.Vacantes;

import java.util.ArrayList;


/**
 * Created by USUARIO on 14/02/2017.
 */

public class InteractorVacante {
    private Context contexto;
    public InteractorVacante(Context contexto){
        this.contexto = contexto;

    }

    public ArrayList<Vacantes> obtenerDatos(){
       /* ArrayList<Vacantes>arrVacantes = new ArrayList<Vacantes>();
        arrVacantes.add(new Vacantes("Desarrollador web", "77777777", "sisma@inventado.com", "Experiencia en programación orientada a objetos"));
        arrVacantes.add(new Vacantes("Tester", "88888888", "tic@inventado.com", "Desarrollador junior para realizar pruebas en sistemas web"));
        arrVacantes.add(new Vacantes("Desarrollador de aplicaciones móviles", "99999999", "appmovil@inventado.com", "Desarrollo con iOS"));
        arrVacantes.add(new Vacantes("Adminstrador de servidores", "66666666", "desapp@inventado.com", "Experiencia de 8 años"));
        */
        BaseDatos bdBase = new BaseDatos(contexto);
        if (bdBase.obtenerTodasVacantes().size() == 0) {
            insertarVacantes(bdBase);
        }
        return bdBase.obtenerTodasVacantes();

    }

    public void insertarVacantes(BaseDatos bdBase){
        ContentValues cvValues = new ContentValues();
        cvValues.put(ConstantesBD.TABLE_POS_NAME, "Desarrollador web");
        cvValues.put(ConstantesBD.TABLE_POS_TEL, "55777755");
        cvValues.put(ConstantesBD.TABLE_POS_EMAIL, "web@rmonkey.mx");
        cvValues.put(ConstantesBD.TABLE_POS_DESCRPTION, "Experiencia en programación orientada a objetos");

        bdBase.insertarVacante(cvValues);

        cvValues = new ContentValues();
        cvValues.put(ConstantesBD.TABLE_POS_NAME, "Tester");
        cvValues.put(ConstantesBD.TABLE_POS_TEL, "55777755");
        cvValues.put(ConstantesBD.TABLE_POS_EMAIL, "web@rmonkey.mx");
        cvValues.put(ConstantesBD.TABLE_POS_DESCRPTION, "Desarrollador junior para realizar pruebas en sistemas web");

        bdBase.insertarVacante(cvValues);

        cvValues = new ContentValues();
        cvValues.put(ConstantesBD.TABLE_POS_NAME, "Desarrollador de aplicaciones móviles");
        cvValues.put(ConstantesBD.TABLE_POS_TEL, "55777750");
        cvValues.put(ConstantesBD.TABLE_POS_EMAIL, "appmovil@rmonkey.mx");
        cvValues.put(ConstantesBD.TABLE_POS_DESCRPTION, "Desarrollo con iOS");

        bdBase.insertarVacante(cvValues);

        cvValues = new ContentValues();
        cvValues.put(ConstantesBD.TABLE_POS_NAME, "Adminstrador de servidores");
        cvValues.put(ConstantesBD.TABLE_POS_TEL, "55777759");
        cvValues.put(ConstantesBD.TABLE_POS_EMAIL, "servidores@rmonkey.mx");
        cvValues.put(ConstantesBD.TABLE_POS_DESCRPTION, "Experiencia de 8 años");

        bdBase.insertarVacante(cvValues);
    }
}
