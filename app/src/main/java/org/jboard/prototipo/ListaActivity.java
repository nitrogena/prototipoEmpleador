package org.jboard.prototipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

import org.jboard.prototipo.Adaptadores.PostulanteAdapter;
import org.jboard.prototipo.Datos.Postulantes;

public class ListaActivity extends AppCompatActivity {

    SwipeRefreshLayout srlRefresh;
    Adapter adaptador;

    ListView lvPostulantes;

    ArrayList<Postulantes> arrPostulantes;

    private RecyclerView rvListaPostulantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvListaPostulantes = (RecyclerView) findViewById(R.id.rvPostulante);

        LinearLayoutManager llmLayout = new LinearLayoutManager(this);
        llmLayout.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager glmLayout = new GridLayoutManager(this, 2);

        rvListaPostulantes.setLayoutManager(llmLayout);
        InicializarListaPostulantes();
        inicializarAdaptador();


        GridLayoutManager glm = new GridLayoutManager(this, 2);



        /* Antes de colocar el adaptador

        arrPostulantes = new ArrayList<Postulantes>();

        arrPostulantes.add(new Postulantes("Nidia Orduña", "77777777", "nidia@inventado.com", R.drawable.pelo_mujer_48));
        arrPostulantes.add(new Postulantes("David Chávez", "88888888", "david@inventado.com", R.drawable.message_48));
        arrPostulantes.add(new Postulantes("Luis Ezcurdia", "99999999", "luis@inventado.com", R.drawable.persona_de_sexo_masculino_48));
        arrPostulantes.add(new Postulantes("Marduk Pérez", "66666666", "marduk@inventado.com", R.drawable.phone_48));


        ArrayList<String> arrNombresPostulantes = new ArrayList<>();
        for (Postulantes postulante: arrPostulantes){
            arrNombresPostulantes.add(postulante.getNombre());}

        lvPostulantes = (ListView) findViewById(R.id.lv_postulantes);
        lvPostulantes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresPostulantes));

        lvPostulantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Explicitos: unir una pantalla a otra
                Intent intent = new Intent(ListaActivity.this, DetalleActivity.class);
                intent.putExtra("nombre", postulantes.get(i).getNombre());
                intent.putExtra("telefono", postulantes.get(i).getTelefono());
                intent.putExtra("correo", postulantes.get(i).getCorreo());

                startActivity(intent);
            }
        });
        */

        srlRefresh = (SwipeRefreshLayout) findViewById(R.id.srlRefresh);


        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescar();
            }
        });

        //finish();
    }

    private void refrescar() {
        /* SIN ADAPTADOR DE RECYCLER VIEW
        String[] arrPlanetas = getResources().getStringArray(R.array.arrPlanetas);
        lvPostulantes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrPlanetas));

        srlRefresh.setRefreshing(false);*/

        //tratar con el intent a si mismo

        Intent intent2 = new Intent(this, ListaActivity.class);

        startActivity(intent2);
        srlRefresh.setRefreshing(false);
    }

    public void InicializarListaPostulantes(){

        arrPostulantes = new ArrayList<Postulantes>();

        arrPostulantes.add(new Postulantes("Nidia Orduña", "77777777", "nidia@inventado.com", R.drawable.pelo_mujer_48));
        arrPostulantes.add(new Postulantes("David Chávez", "88888888", "david@inventado.com", R.drawable.persona_de_sexo_masculino_48));
        arrPostulantes.add(new Postulantes("Luis Ezcurdia", "99999999", "luis@inventado.com", R.drawable.persona_de_sexo_masculino_48));
        arrPostulantes.add(new Postulantes("Marduk Pérez", "66666666", "marduk@inventado.com", R.drawable.persona_de_sexo_masculino_48));



    }

    public void inicializarAdaptador(){
        PostulanteAdapter paAdaptador = new PostulanteAdapter(arrPostulantes, this);
        rvListaPostulantes.setAdapter(paAdaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcion_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.moContactado:
                refrescar();
                break;
            case R.id.moPendiente:
                refrescar();
                break;
            case R.id.moOculto:
                refrescar();
                break;
            case R.id.moNuevo:
                refrescar();
                break;

            case R.id.moAcerca:
                mostrarInformativo("acerca");
                break;
            case R.id.moCreditos:
                mostrarInformativo("creditos");
                break;
            /*case R.id.moAvRefresh:
                refrescar();
                break;*/

        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarInformativo(String strOpcion){

        Intent intent = new Intent(this, InformativoActivity.class);
        intent.putExtra("texto", strOpcion);
        startActivity(intent);
    }


}
