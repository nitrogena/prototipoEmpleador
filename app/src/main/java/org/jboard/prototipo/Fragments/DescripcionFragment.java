package org.jboard.prototipo.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jboard.prototipo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescripcionFragment extends Fragment {

    TextView tvVacante;
    TextView tvTelefono;
    TextView tvCorreo;
    //ImageView ivFoto;
    TextView tvDescripcion;

    //ImageButton ibContactar;
    //ImageButton ibOcultar;
    //ImageButton ibPreguntar;

    public DescripcionFragment() {
        // Required empty public constructor
    }

    public static DescripcionFragment newInstance(String vacante, String telefono, String correo, String descripcion){
        DescripcionFragment descripcionFragment = new DescripcionFragment();
        Bundle bundle  = new Bundle();
        bundle.putString("vacante", vacante);
        bundle.putString("telefono", telefono);
        bundle.putString("correo", correo);
        bundle.putString("descripcion", descripcion);
        descripcionFragment.setArguments(bundle);
        return descripcionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_descripcion, container, false);



       /*Bundle parametros = getIntent().getExtras();
        String vacante = parametros.getString("vacante");
        String telefono = parametros.getString("telefono");
        String correo = parametros.getString("correo");
        String descripcion = parametros.getString("descripcion");*/

       /*Bundle parametros = getArguments();
        String vacante = parametros.getString("vacante");
        String telefono = parametros.getString("telefono");
        String correo = parametros.getString("correo");
        String descripcion = parametros.getString("descripcion"); */

        //int foto = parametros.getInt("foto");

        String vacante = getArguments().getString("vacante", "");
        String telefono = getArguments().getString("telefono");
        String correo = getArguments().getString("correo");
        String descripcion = getArguments().getString("descripcion");

        tvVacante = (TextView) view.findViewById(R.id.tvVacante);
        tvTelefono = (TextView) view.findViewById(R.id.tvTel);
        tvCorreo = (TextView) view.findViewById(R.id.tvCorreo);
        tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);

        //ivFoto = (ImageView) view.findViewById(R.id.ivFoto);

        //Log.i("telefono", telefono);

        tvVacante.setText(vacante);
        tvCorreo.setText(correo);
        tvTelefono.setText(telefono);
        tvDescripcion.setText(descripcion);

        //ivFoto.setImageResource(foto);

        //consultar();

        tvTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hacerLlamada(view);
            }
        });
        tvCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarCorreo(view);
            }
        });

        //editarFoto();

        return view;
    }

    public void hacerLlamada(View view) {
        TextView tvTelefono = (TextView) view.findViewById(R.id.tvTel);
        String telefono = tvTelefono.getText().toString();
        //Intent implicito

        Log.i("telefonoLlamar", telefono);
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        getActivity().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));

    }

    public void mandarCorreo(View view) {
        String correo = tvCorreo.getText().toString();
        Intent itCorreo = new Intent((Intent.ACTION_SEND));
        itCorreo.setData(Uri.parse("mailto:"));
        itCorreo.putExtra(Intent.EXTRA_EMAIL, correo);
        itCorreo.setType("message/rfc822");
        getActivity().startActivity(Intent.createChooser(itCorreo, "Correo-e"));
    }




}
