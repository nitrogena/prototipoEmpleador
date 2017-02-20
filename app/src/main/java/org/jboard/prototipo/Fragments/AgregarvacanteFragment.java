package org.jboard.prototipo.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jboard.prototipo.BD.BaseDatos;
import org.jboard.prototipo.BD.ConstantesBD;
import org.jboard.prototipo.R;
import org.jboard.prototipo.VacanteActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarvacanteFragment extends Fragment {

    private Button btnAgregarVacante;
    private Button btnLimpiar;
    private EditText etVacante;
    private EditText etDescripcion;
    private EditText etCorreo;
    private EditText etTelefono;
    private Context contexto;
    private View pbProgress;
    private View svScroll;

    public AgregarvacanteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregarvacante, container, false);

        etVacante = (EditText) view.findViewById(R.id.fav_etVacante);
        etDescripcion = (EditText) view.findViewById(R.id.fav_etDesc);
        etCorreo = (EditText) view.findViewById(R.id.fav_etCorreo);
        etTelefono = (EditText) view.findViewById(R.id.fav_etTel);

        svScroll = view.findViewById(R.id.svScroll);
        pbProgress = view.findViewById(R.id.pbProgress);

        String strVacante = etVacante.getText().toString();
        Log.i("vacante1: ", strVacante);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.atras_48);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        cancelar(view);
        agregarVacante(view);

        return view;
    }

    public void limparCampos(View view) {
        etVacante.setText("");
        etDescripcion.setText("");
        etCorreo.setText("");
        etTelefono.setText("");
    }

    public void cancelar(View view){
        btnLimpiar = (Button) view.findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();
            }
        });
    }

    public void agregarVacante(View view2) {
        btnAgregarVacante = (Button) view2.findViewById(R.id.btnAgregarVacante);
        btnAgregarVacante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (!autenticar(view2)) {
                    //showProgress(true);
                    String strVacante = etVacante.getText().toString();
                    String strDesc = etDescripcion.getText().toString();
                    String strCorreo = etCorreo.getText().toString();
                    String strTelefono = etTelefono.getText().toString();

                    BaseDatos bdBase = new BaseDatos(getActivity());

                    ContentValues cvValues = new ContentValues();
                    cvValues.put(ConstantesBD.TABLE_POS_NAME, strVacante);
                    cvValues.put(ConstantesBD.TABLE_POS_TEL, strTelefono);
                    cvValues.put(ConstantesBD.TABLE_POS_EMAIL, strCorreo);
                    cvValues.put(ConstantesBD.TABLE_POS_DESCRPTION, strDesc);

                    bdBase.insertarVacante(cvValues);

                    Toast.makeText(getActivity(), R.string.avf_vacanteReg, Toast.LENGTH_LONG).show();
                    limparCampos(view2);
                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        ((VacanteActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((VacanteActivity) getActivity()).getSupportActionBar().show();
    }

    private Boolean autenticar(View view) {
        etVacante.setError(null);
        etDescripcion.setError(null);
        etCorreo.setError(null);
        etTelefono.setError(null);


        String strCorreo = etCorreo.getText().toString();
        String strTelefono = etTelefono.getText().toString();
        String strVacante = etVacante.getText().toString();
        String strDescrpcion = etDescripcion.getText().toString();

        boolean blBandera = false;
        View focusView = null;


        if (TextUtils.isEmpty(strVacante)) {
            etVacante.setError(getString(R.string.aa_msgErrorRequerido));
            focusView = etVacante;
            blBandera = true;
        } else if (!TextUtils.isEmpty(strVacante) && !validarCampo(strVacante, 6)) {
            etVacante.setError(getString(R.string.avf_msgErrorVacante));
            focusView = etVacante;
            blBandera = true;
        }


        if (TextUtils.isEmpty(strDescrpcion)) {
            etDescripcion.setError(getString(R.string.aa_msgErrorRequerido));
            focusView = etDescripcion;
            blBandera = true;
        } else if (!TextUtils.isEmpty(strDescrpcion) && !validarCampo(strDescrpcion, 10)) {
            etDescripcion.setError(getString(R.string.avf_msgErrorDescripcion));
            focusView = etDescripcion;
            blBandera = true;
        }
        if (TextUtils.isEmpty(strTelefono)) {
            etTelefono.setError(getString(R.string.aa_msgErrorRequerido));
            focusView = etTelefono;
            blBandera = true;
        } else if (!TextUtils.isEmpty(strTelefono) && !validarCampo(strTelefono, 8)) {
            etTelefono.setError(getString(R.string.avf_msgErrorTelefono));
            focusView = etTelefono;
            blBandera = true;
        }
        if (TextUtils.isEmpty(strCorreo)) {
            etCorreo.setError(getString(R.string.aa_msgErrorRequerido));
            focusView = etCorreo;
            blBandera = true;
        } else if (!validarCorreo(strCorreo)) {
            etCorreo.setError(getString(R.string.aa_msgErrorCorreo));
            focusView = etCorreo;
            blBandera = true;
        }

        if (blBandera) {

            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            //isAutentica = new AutenticaActivity.IniciarSesion(strCorreo, strContrasenia);
            //isAutentica.execute((Void) null);
        }
        return blBandera;
    }

    private boolean validarCorreo(String strCorreo) {
        //TODO: Replace this with your own logic
        Boolean blValida = false;
        if (strCorreo.contains("@")) {
            if (strCorreo.contains(".")) {
                if (strCorreo.length() > 5) {
                    blValida = true;
                }
            }

        }
        return blValida;

    }

    private boolean validarCampo(String strTexto, int intNo) {
        //TODO: Replace this with your own logic
        return strTexto.length() >= intNo;
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            svScroll.setVisibility(show ? View.GONE : View.VISIBLE);
            svScroll.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    svScroll.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            pbProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            pbProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    pbProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            pbProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            svScroll.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}