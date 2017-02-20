package org.jboard.prototipo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import org.jboard.prototipo.Adaptadores.PostulanteAdapter;
import org.jboard.prototipo.Datos.Postulantes;
import org.jboard.prototipo.R;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class PostulantesFragment extends Fragment {
    SwipeRefreshLayout srlRefresh;
    Adapter adaptador;

    ListView lvPostulantes;

    ArrayList<Postulantes> arrPostulantes;

    private RecyclerView rvListaPostulantes;

    public PostulantesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_postulantes, container, false);


        rvListaPostulantes = (RecyclerView) view.findViewById(R.id.rvPostulante);

        LinearLayoutManager llmLayout = new LinearLayoutManager(getActivity());
        llmLayout.setOrientation(LinearLayoutManager.VERTICAL);

        rvListaPostulantes.setLayoutManager(llmLayout);
        inicializarListaPostulantes();
        inicializarAdaptador();

        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srlRefresh);


        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescar();
            }
        });
        return view;
    }

    private void refrescar() {
        //Intent intent2 = new Intent(this, ListaActivity.class);
        //startActivity(intent2);
        srlRefresh.setRefreshing(false);
    }

    public void inicializarListaPostulantes(){
        arrPostulantes = new ArrayList<Postulantes>();
        arrPostulantes.add(new Postulantes("Nidia Orduña", "77777777", "nidia@inventado.com", R.drawable.pelo_mujer_48));
        arrPostulantes.add(new Postulantes("David Chávez", "88888888", "david@inventado.com", R.drawable.persona_de_sexo_masculino_48));
        arrPostulantes.add(new Postulantes("Luis Ezcurdia", "99999999", "luis@inventado.com", R.drawable.persona_de_sexo_masculino_48));
        arrPostulantes.add(new Postulantes("Marduk Pérez", "66666666", "marduk@inventado.com", R.drawable.persona_de_sexo_masculino_48));
    }

    public void inicializarAdaptador(){
        PostulanteAdapter paAdaptador = new PostulanteAdapter(arrPostulantes, getActivity());
        rvListaPostulantes.setAdapter(paAdaptador);
    }

}
