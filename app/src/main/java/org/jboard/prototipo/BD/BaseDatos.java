package org.jboard.prototipo.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jboard.prototipo.Datos.Vacantes;

import java.util.ArrayList;

/**
 * Created by USUARIO on 14/02/2017.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context contexto;

    public BaseDatos(Context contexto) {
        super(contexto, ConstantesBD.BASE_DATOS, null, ConstantesBD.VERSION_BD);
        this.contexto = contexto;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCrearTablaOrg = "CREATE TABLE " + ConstantesBD.TABLE_ORG + "(" +
                ConstantesBD.TABLE_ORG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_ORG_NAME + " TEXT, " +
                ConstantesBD.TABLE_ORG_EMAIL + " TEXT, " +
                ConstantesBD.TABLE_ORG_ORG + " TEXT, " +
                ConstantesBD.TABLE_ORG_TYPE + " TEXT, " +
                ConstantesBD.TABLE_ORG_PASSWORD + " TEXT, " +
                ConstantesBD.TABLE_ORG_PHOTO + " INTEGER " +
                ")";

        String queryCrearTablaPos = "CREATE TABLE " + ConstantesBD.TABLE_POS + "(" +
                ConstantesBD.TABLE_POS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLE_POS_NAME + " TEXT, " +
                ConstantesBD.TABLE_POS_TEL + " TEXT, " +
                ConstantesBD.TABLE_POS_EMAIL + " TEXT, " +
                ConstantesBD.TABLE_POS_DESCRPTION + " TEXT, " +
                ConstantesBD.TABLE_POS_ORG_ID + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBD.TABLE_POS_ORG_ID + ")" +
                "REFERENCES " + ConstantesBD.TABLE_ORG + "(" + ConstantesBD.TABLE_ORG_ID + ")" +
                ")";


        sqLiteDatabase.execSQL((queryCrearTablaOrg));
        sqLiteDatabase.execSQL(queryCrearTablaPos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_POS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_ORG);
        onCreate(sqLiteDatabase);


    }

    public ArrayList<Vacantes> obtenerTodasVacantes(){
        ArrayList<Vacantes> arrLstVac = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBD.TABLE_POS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Vacantes vacante = new Vacantes(registros.getString(1), registros.getString(2), registros.getString(3),
                    registros.getString(4));
            //String vacante, String telefono, String correo, String descripcion
            vacante.setId(registros.getInt(0));
            vacante.setVacante(registros.getString(1));
            vacante.setTelefono(registros.getString(2));
            vacante.setCorreo(registros.getString(3));
            vacante.setDescripcion(registros.getString(4));

            arrLstVac.add(vacante);

        }
        db.close();
        return arrLstVac;
    }

    public void insertarVacante(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_POS, null, contentValues);
        db.close();
    }

    public void insertarEmpresa(ContentValues contentValues){
        SQLiteDatabase sqldLite = this.getWritableDatabase();
        sqldLite.insert(ConstantesBD.TABLE_ORG, null, contentValues);
    }
}
