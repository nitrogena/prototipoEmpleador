package org.jboard.prototipo.Adaptadores;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import org.jboard.prototipo.Datos.Vacantes;
import org.jboard.prototipo.DescripcionActivity;
import org.jboard.prototipo.DetalleActivity;
import org.jboard.prototipo.Fragments.DescripcionFragment;
import org.jboard.prototipo.R;

/**
 * Created by USUARIO on 11/02/2017.
 */

public class VacanteAdapter extends RecyclerView.Adapter<VacanteAdapter.VacanteViewHolder>{


    ArrayList<Vacantes> arrLstVacantes;
    Activity activity;

    public VacanteAdapter(ArrayList<Vacantes> arrLstVacantes, Activity activity) {
        this.arrLstVacantes = arrLstVacantes;
        this.activity = activity;
    }

    @Override
    public VacanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vwView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_vacante, parent, false);
        return new VacanteViewHolder(vwView);
    }

    @Override
    public void onBindViewHolder(VacanteViewHolder vvhHolder, int position) {
        final Vacantes vsVacante = arrLstVacantes.get(position);
        // vvhHolder.ivFotoCV.setImageResource(vsVacante.getFoto());
        vvhHolder.tvVacanteCV.setText(vsVacante.getVacante());
        vvhHolder.tvTelCV.setText(vsVacante.getTelefono());
        vvhHolder.tvCorreoCV.setText(vsVacante.getCorreo());
        vvhHolder.tvDescCV.setText(vsVacante.getDescripcion());


        vvhHolder.llCv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Toast.makeText(activity, vsVacante.getVacante(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activity, DescripcionActivity.class);
                    /**/intent.putExtra("vacante", vsVacante.getVacante());
                intent.putExtra("telefono", vsVacante.getTelefono());
                intent.putExtra("correo", vsVacante.getCorreo());
                //intent.putExtra("foto", vsVacante.getFoto());
                intent.putExtra("descripcion", vsVacante.getDescripcion());
/**/
                activity.startActivity(intent);

                    /*Bundle bundle = new Bundle();
                    bundle.putString("vacante", vsVacante.getVacante());
                    bundle.putString("telefono", vsVacante.getTelefono());
                    bundle.putString("correo", vsVacante.getCorreo());
                    bundle.putString("descripcion", vsVacante.getDescripcion());
                    DescripcionFragment myfragment = new DescripcionFragment();
                    myfragment.setArguments(bundle);
                    Intent intent = new Intent(activity, DescripcionActivity.class);
                    activity.startActivity(intent);*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrLstVacantes.size();
    }

    public static class VacanteViewHolder extends RecyclerView.ViewHolder{

        //private ImageView ivFotoCV;
        private TextView tvVacanteCV;
        private TextView tvTelCV;
        private TextView tvCorreoCV;
        private TextView tvDescCV;
        private LinearLayout llCv;

        public VacanteViewHolder(View itemView) {
            super(itemView);
            // ivFotoCV = (ImageView) itemView.findViewById(R.id.ivFotoCV);
            tvVacanteCV = (TextView) itemView.findViewById(R.id.tvVacanteCV);
            tvTelCV = (TextView) itemView.findViewById(R.id.tvTelCV);
            tvCorreoCV = (TextView) itemView.findViewById(R.id.tvCorreoCV);
            tvDescCV = (TextView) itemView.findViewById(R.id.tvDescCV);

            llCv = (LinearLayout) itemView.findViewById(R.id.cv_ll);

        }
    }

}
