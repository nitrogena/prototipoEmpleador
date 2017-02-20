package org.jboard.prototipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

import org.jboard.prototipo.Adaptadores.VacanteAdapter;
import org.jboard.prototipo.Datos.Vacantes;
import org.jboard.prototipo.Fragments.AgregarvacanteFragment;
import org.jboard.prototipo.Fragments.VacantesFragment;
import org.jboard.prototipo.vacantes.InterfaceVacante;
import org.jboard.prototipo.vacantes.InterfaceVacantePresenter;
import org.jboard.prototipo.vacantes.VacantePresenter;

public class VacanteActivity extends AppCompatActivity {

    //SwipeRefreshLayout srlRefresh;
    Adapter adaptador;

    ListView lvVacantes;

    ArrayList<Vacantes> arrVacantes;

    private RecyclerView rvListaVacantes;
    //private InterfaceVacantePresenter ivpPresenter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacante);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);

        setSupportActionBar(actionBar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //rvListaVacantes = (RecyclerView) findViewById(R.id.rvVacante);
        //ivpPresenter = new VacantePresenter(this, getBaseContext());

        /*vista presentador
        LinearLayoutManager llmLayout = new LinearLayoutManager(this);
        llmLayout.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager glmLayout = new GridLayoutManager(this, 2);
        rvListaVacantes.setLayoutManager(llmLayout);
*/
        /*inicializarListaVacantes();
        inicializarAdaptador();*/

        /*srlRefresh = (SwipeRefreshLayout) findViewById(R.id.srlRefresh);
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescar();
            }
        });*/
        //finish();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.av_flVacantes, new VacantesFragment());
        ft.commit();

        navigationView = (NavigationView) findViewById(R.id.navigationActivity_navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.menuuno_opcionUno:
                        Intent intent3 = new Intent(VacanteActivity.this, ConsultaregistroActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.menuuno_opcionDos:
                        mostrarInformativo("creditos");
                        return true;
                    case R.id.menuuno_opcionTres:
                        //Snackbar.make(findViewById(android.R.id.content), "Opcion tres", Snackbar.LENGTH_SHORT).show();
                        mostrarInformativo("ayuda");
                        return true;
                    case R.id.menuuno_opcionCuatro:
                        Intent intent = new Intent(VacanteActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("salir", true);
                        startActivity(intent);
                        finish();
                        return true;
                }
                return true;        //se supone que no va

            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, actionBar, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
            }

        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //sincronizar cuando abre o cuando lo cerramos
        actionBarDrawerToggle.syncState();

    }

    private void refrescar() {
        Intent intent2 = new Intent(this, VacanteActivity.class);
        startActivity(intent2);
        //srlRefresh.setRefreshing(false);
    }
    /*presenter
        public void inicializarListaVacantes(){
            arrVacantes = new ArrayList<Vacantes>();
            arrVacantes.add(new Vacantes("Desarrollador web", "77777777", "sisma@inventado.com", "Experiencia en programación orientada a objetos"));
            arrVacantes.add(new Vacantes("Tester", "88888888", "tic@inventado.com", "Desarrollador junior para realizar pruebas en sistemas web"));
            arrVacantes.add(new Vacantes("Desarrollador de aplicaciones móviles", "99999999", "appmovil@inventado.com", "Desarrollo con iOS"));
            arrVacantes.add(new Vacantes("Adminstrador de servidores", "66666666", "desapp@inventado.com", "Experiencia de 8 años"));
        }

        public void inicializarAdaptador(){
           VacanteAdapter vaAdaptador = new VacanteAdapter(arrVacantes, this);
            rvListaVacantes.setAdapter(vaAdaptador);
        }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcion_vacante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.movAgregar:
                //refrescar();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.av_flVacantes, new AgregarvacanteFragment());
                ft.addToBackStack(null);
                ft.commit();

                break;
            case R.id.movTodasVacantes:
                refrescar();
                break;
            case R.id.movVacantesDisponibles:
                refrescar();
                break;
            case R.id.movVacantesOcupadas:
                refrescar();
                break;
            case R.id.movVacantesSinPostulantes:
                refrescar();
                break;
            case R.id.moAcerca:
                mostrarInformativo("acerca");
                break;
            case R.id.moCreditos:
                mostrarInformativo("creditos");
                break;
            case R.id.movAgregarV:
                FragmentTransaction ftV = getSupportFragmentManager().beginTransaction();
                ftV.replace(R.id.av_flVacantes, new AgregarvacanteFragment());
                ftV.addToBackStack(null);
                ftV.commit();

                break;
            case R.id.movAvRefresh:
                refrescar();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarInformativo(String strOpcion){
        Intent intent = new Intent(this, InformativoActivity.class);
        intent.putExtra("texto", strOpcion);
        startActivity(intent);
    }

    /*@Override
    public void generarLlv() {
        LinearLayoutManager llmLayout = new LinearLayoutManager(this);
        llmLayout.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager glmLayout = new GridLayoutManager(this, 2);
        rvListaVacantes.setLayoutManager(llmLayout);
    }

    @Override
    public VacanteAdapter generarAdapter() {
        VacanteAdapter vaAdaptador = new VacanteAdapter(arrVacantes, this);
        return vaAdaptador;
    }

    @Override
    public void inicializarAdaptador(VacanteAdapter vaAdaptador) {
        rvListaVacantes.setAdapter(vaAdaptador);
    }*/


}