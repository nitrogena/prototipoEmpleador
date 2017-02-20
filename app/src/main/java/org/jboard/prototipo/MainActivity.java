package org.jboard.prototipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnConsultar;
    Button btnVerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionBar);

        consultar();
        //verPerfil();

        //usarCamara();

        //Toast.makeText(this, getResources().getString(R.string.am_onCreate), Toast.LENGTH_SHORT).show();
        if( getIntent().getBooleanExtra("salir", false)){
            finish();
        }
    }
    /*
        private void verPerfil() {
            btnVerPerfil = (Button) findViewById(R.id.btnVerPerfil);
            btnVerPerfil.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent iPerfil = new Intent(MainActivity.this, PerfilActivity.class);
                    startActivity(iPerfil);
                }
            });

        }
    */
    public void consultar(){
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                //Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                Intent intent = new Intent(MainActivity.this, AutenticaActivity.class);
                startActivity(intent);
            }
        });

    }
/*
    private void usarCamara() {
        Button btnUsarCamara = (Button) findViewById(R.id.btnUsarCamara);
        btnUsarCamara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent iPerfil = new Intent(MainActivity.this, CamaraActivity.class);
                startActivity(iPerfil);
            }
        });

    }
*/
  /*
    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, getResources().getString(R.string.am_onStart), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, getResources().getString(R.string.am_onResume), Toast.LENGTH_SHORT).show();
    }

    //los de arriba está corriendo

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(this, getResources().getString(R.string.am_onRestart), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, getResources().getString(R.string.am_onPause), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, getResources().getString(R.string.am_onStop), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, getResources().getString(R.string.am_onDestroy), Toast.LENGTH_SHORT).show();
    }
    */
}
