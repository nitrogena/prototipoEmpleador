package org.jboard.prototipo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreaActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView actvCorreo;
    private EditText etContrasenia;
    private View pbProgress;
    private View svScroll;
    private Button btnIngresar;
    private IniciarSesionC isAutentica = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionBar);
        //btnIngresar = (Button) findViewById(R.id.btnIngresar);

        /*btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticar();
            }
        });
*/
        findViewById(R.id.btnIngresar).setOnClickListener(this);
        findViewById(R.id.btnLimpiar).setOnClickListener(this);

        svScroll = findViewById(R.id.svScroll);
        pbProgress = findViewById(R.id.pbProgress);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnIngresar:
                autenticar(view);
                break;
            case R.id.btnLimpiar:
                limpiarCampos(view);
                break;
        }
    }

    public void limpiarCampos(View view){
        actvCorreo = (AutoCompleteTextView) findViewById(R.id.actvCorreo);
        etContrasenia = (EditText) findViewById(R.id.etContrasenia);
        actvCorreo.setText("");
        etContrasenia.setText("");
    }

    public void  registrarUsuario(){
        try{

            actvCorreo = (AutoCompleteTextView) findViewById(R.id.actvCorreo);
            etContrasenia = (EditText) findViewById(R.id.etContrasenia);


            String strUsuario = actvCorreo.getText().toString();
            String strContrasenia = etContrasenia.getText().toString();

            SharedPreferences spAutentica = getSharedPreferences("Autenticacion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = spAutentica.edit();

            editor.putString("usuario", strUsuario);
            editor.putString("contrasenia", strContrasenia);
            editor.commit();

            Toast.makeText(this, R.string.ra_mensajeRegistro, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(CreaActivity.this, RegistroActivity.class);
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, R.string.ra_mensajeE, Toast.LENGTH_SHORT).show();
        }
    }



    public boolean mostrarPreferencia(String strUsuarioA, String strContraseniaA){


        SharedPreferences spAutentica = getSharedPreferences("Autenticacion", Context.MODE_PRIVATE);
        String strUsuario = spAutentica.getString("usuario", "No existe usuario");
        String strContrasenia = spAutentica.getString("contrasenia", "No existe contrasenia");
        String strToken = spAutentica.getString("token", "No existe token");

        //TextView tvDesc = (TextView) findViewById(R.id.ar_tvDesc);
        if (strUsuarioA.equals(strUsuario)){
            if (strContraseniaA.equals(strContrasenia)){

                if (strToken.equals("No existe token")){
                    return true;
                }
                else{
                    Intent intent = new Intent(this, VacanteActivity.class);
                    intent.putExtra("token", strToken);
                    startActivity(intent);
                }
            }
        }

        String strValores = "\nUsuario: " +strUsuario+ "\nContrasenia: " + strContrasenia;

        //tvDesc.setText(strValores);
        return false;
    }

    private void autenticar(View view) {
        actvCorreo = (AutoCompleteTextView) findViewById(R.id.actvCorreo);
        etContrasenia = (EditText) findViewById(R.id.etContrasenia);


        if (isAutentica != null) {
            return;
        }

        // Reset errors.
        actvCorreo.setError(null);
        etContrasenia.setError(null);

        // Store values at the time of the login attempt.
        String strCorreo = actvCorreo.getText().toString();
        String strContrasenia = etContrasenia.getText().toString();

        boolean blBandera = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(strContrasenia) && !validarContrasenia(strContrasenia)) {
            etContrasenia.setError(getString(R.string.aa_msgErrorContrasenia));
            focusView = etContrasenia;
            blBandera = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(strCorreo)) {
            actvCorreo.setError(getString(R.string.aa_msgErrorRequerido));
            focusView = actvCorreo;
            blBandera = true;
        } else if (!validarCorreo(strCorreo)) {
            actvCorreo.setError(getString(R.string.aa_msgErrorCorreo));
            focusView = actvCorreo;
            blBandera = true;
        }

        if (blBandera) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            isAutentica = new IniciarSesionC(strCorreo, strContrasenia);
            isAutentica.execute((Void) null);
        }
    }

    private boolean validarCorreo(String strCorreo) {
        //TODO: Replace this with your own logic
        Boolean blValida = false;
        if (strCorreo.contains("@")){
            if (strCorreo.contains(".")){
                if (strCorreo.length() > 5){
                    blValida = true;
                }
            }

        }
        return  blValida;

    }

    private boolean validarContrasenia(String strContrasenia) {
        //TODO: Replace this with your own logic
        return strContrasenia.length() > 6;
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
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


    public class IniciarSesionC extends AsyncTask<Void, Void, Boolean> {

        private final String strUsuario;
        private final String strContrasenia;

        IniciarSesionC(String strUsuarioA, String strContraseniaA) {
            strUsuario = strUsuarioA;
            strContrasenia = strContraseniaA;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            Boolean blRes = false;

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            blRes = true;
            //mostrarPreferencia(strUsuario, strContrasenia);
            /*
            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(strUsuario)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(strContrasenia);
                }
                else{
                    return false;
                }
            }*/

            //  register the new account here.
            return blRes;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            isAutentica = null;
            showProgress(false);

            if (success) {
                //finish();
                registrarUsuario();
                //Intent intent = new Intent(CreaActivity.this, RegistroActivity.class);
                //startActivity(intent);
            }
            else {
                /*
                Snackbar.make(etContrasenia, R.string.aa_preguntaRegistrar, Snackbar.LENGTH_INDEFINITE)
                        .setAction(android.R.string.ok, new View.OnClickListener() {
                            public void onClick(View v) {
                                registrarUsuario(v);
                            }
                        })
                        .show();
                //etContrasenia.setError(getString(R.string.aa_msgError));
                //etContrasenia.requestFocus();
                */
            }
        }

        @Override
        protected void onCancelled() {
            isAutentica = null;
            showProgress(false);
        }
    }
}
