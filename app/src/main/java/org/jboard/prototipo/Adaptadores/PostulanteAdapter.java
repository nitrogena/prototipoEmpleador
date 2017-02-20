package org.jboard.prototipo.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import org.jboard.prototipo.Datos.Postulantes;
import org.jboard.prototipo.DetalleActivity;
import org.jboard.prototipo.R;

/**
 * Created by USUARIO on 28/01/2017.
 */

public class PostulanteAdapter extends RecyclerView.Adapter<PostulanteAdapter.PostulanteViewHolder>{

    ArrayList<Postulantes> arrLstPostulantes;
    Activity activity;

    public PostulanteAdapter(ArrayList<Postulantes> arrLstPostulantes, Activity activiity) {
        this.arrLstPostulantes = arrLstPostulantes;
        this.activity = activiity;
    }

    @Override
    public PostulanteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vwView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_postulante, parent, false);
        return new PostulanteViewHolder(vwView);
    }

    @Override
    public void onBindViewHolder(PostulanteViewHolder pvhHolder, int position) {
        final Postulantes psPostulante = arrLstPostulantes.get(position);
        pvhHolder.ivFotoCV.setImageResource(psPostulante.getFoto());
        pvhHolder.tvNombreCV.setText(psPostulante.getNombre());
        pvhHolder.tvTelCV.setText(psPostulante.getTelefono());
        pvhHolder.tvCorreoCV.setText(psPostulante.getCorreo());

        //pvhHolder.ivFotoCV.setOnClickListener(new View.OnClickListener(){
        pvhHolder.llCvP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Toast.makeText(activity, psPostulante.getNombre(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activity, DetalleActivity.class);
                intent.putExtra("nombre", psPostulante.getNombre());
                intent.putExtra("telefono", psPostulante.getTelefono());
                intent.putExtra("correo", psPostulante.getCorreo());
                intent.putExtra("foto", psPostulante.getFoto());

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrLstPostulantes.size();
    }

    public static class PostulanteViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivFotoCV;
        private TextView tvNombreCV;
        private TextView tvTelCV;
        private TextView tvCorreoCV;
        private LinearLayout llCvP;

        public PostulanteViewHolder(View itemView) {
            super(itemView);
            ivFotoCV = (ImageView) itemView.findViewById(R.id.ivFotoCV);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelCV = (TextView) itemView.findViewById(R.id.tvTelCV);
            tvCorreoCV = (TextView) itemView.findViewById(R.id.tvCorreoCV);
            llCvP = (LinearLayout) itemView.findViewById(R.id.llCvP);

        }
    }


}