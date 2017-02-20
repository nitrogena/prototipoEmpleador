package org.jboard.prototipo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class ConsultaregistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmpresa;
    private EditText etSector;
    private Button btnRegistrar;

    private TextView tvCorreo;
    private ImageView ivFoto;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultaregistro);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionBar);

        /*btnRegistrar = (Button) findViewById(R.id.ar_btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generarArchivo(view);
            }
        });*/
        tvCorreo = (TextView) findViewById(R.id.tvCorreo);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);

        etEmpresa = (EditText) findViewById(R.id.ar_etEmpresa);
        etSector = (EditText) findViewById(R.id.ar_etSector);

        mostrarCorreo();

        tvCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarCorreo(view);
            }
        });


        findViewById(R.id.ar_btnRegistrar).setOnClickListener(this);
        findViewById(R.id.ar_btnLimpiar).setOnClickListener(this);
        findViewById(R.id.tvEditarFoto).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ar_btnRegistrar:
                generarArchivo(view);
                break;
            case R.id.ar_btnLimpiar:
                limparCampos(view);
                break;
            case R.id.tvEditarFoto:
                editarFoto(view);

        }
    }

    private void editarFoto(View view) {
        Toast.makeText(this, R.string.mensaje6_camara, Toast.LENGTH_SHORT).show();
    }

    public void limparCampos(View view){
        etEmpresa = (EditText) findViewById(R.id.ar_etEmpresa);
        etSector = (EditText) findViewById(R.id.ar_etSector);
        //etEmpresa.setText("");
        //etSector.setText("");
        Intent intent = new Intent(ConsultaregistroActivity.this, VacanteActivity.class);
        startActivity(intent);
    }

    public void  generarArchivo(View view){
        try{
            etEmpresa = (EditText) findViewById(R.id.ar_etEmpresa);
            etSector = (EditText) findViewById(R.id.ar_etSector);
            String strEmpresa = etEmpresa.getText().toString();
            String strSector = etSector.getText().toString();
            String strEspacio = " ";

            String strToken = "tk1:" +strEmpresa+ "tk2:" + strSector;

            FileOutputStream fosFileOS = null;
            fosFileOS = openFileOutput("RegistroArchivo.txt", Context.MODE_APPEND);
            fosFileOS.write(strEmpresa.getBytes());
            fosFileOS.write(strEspacio.getBytes());
            fosFileOS.write(strSector.getBytes());
            fosFileOS.write(strEspacio.getBytes());
            fosFileOS.close();



            //SharedPreferences spVisto = getSharedPreferences("Visto", Context.MODE_PRIVATE);
            SharedPreferences spVisto = getSharedPreferences("Autenticacion", Context.MODE_APPEND);
            SharedPreferences.Editor editor = spVisto.edit();

            editor.putString("empresa", strEmpresa);
            editor.putString("sector", strSector);
            editor.putString("token", strToken);
            editor.commit();
            Toast.makeText(this, R.string.cra_msgModifica, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ConsultaregistroActivity.this, VacanteActivity.class);
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, R.string.ra_mensajeE, Toast.LENGTH_LONG).show();
        }
    }

    public void mostrarCorreo(){


        SharedPreferences spAutentica = getSharedPreferences("Autenticacion", Context.MODE_PRIVATE);
        String strUsuario = spAutentica.getString("usuario", "No existe usuario");
        String strContrasenia = spAutentica.getString("contrasenia", "No existe contrasenia");
        String strToken = spAutentica.getString("token", "No existe token");
        String strEmpresa = spAutentica.getString("empresa", "No existe empresa");
        String strSector = spAutentica.getString("sector", "No existe sector");



        String strValores = "\nUsuario: " +strUsuario+ "\nContrasenia: " + strContrasenia;

        tvCorreo.setText(strUsuario);
        etEmpresa.setText(strEmpresa);
        etSector.setText(strSector);

    }


    public void verMenuPopup(View view) {
        ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        PopupMenu pmPopupMenu = new PopupMenu(this, ivFoto);
        pmPopupMenu.getMenuInflater().inflate(R.menu.menu_popup, pmPopupMenu.getMenu());

        /*camara empieza*/
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //revisar el estatus del permiso
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            //Soliicitar permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                //El permiso ya fue otorgado previamente
                Toast.makeText(this, R.string.mensaje1_camara, Toast.LENGTH_SHORT).show();

            }else{

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }
        if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                //El permiso ya fue otorgado previamente
                Toast.makeText(this, R.string.mensaje1_camara, Toast.LENGTH_SHORT).show();

            }else{

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }



        /*camara finaliza*/

        pmPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mpuEditarFotoCam:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.mpu_Editar), Toast.LENGTH_LONG).show();

                        /*camara empieza*/

                        //Creamos el Intent para llamar a la Camara
                        Intent cameraIntent = new Intent(
                                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        //Creamos una carpeta en la memeria del terminal
                        File imagesFolder = new File(
                                Environment.getExternalStorageDirectory(), "PrototEmplea");
                        imagesFolder.mkdirs();
                        //añadimos el nombre de la imagen
                        File image = new File(imagesFolder, "fotoOrg.jpg");
                        Uri uriSavedImage = Uri.fromFile(image);
                        //Le decimos al Intent que queremos grabar la imagen
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                        //Lanzamos la aplicacion de la camara con retorno (forResult)
                        startActivityForResult(cameraIntent, 1);

                        /*camara finaliza*/


                        break;
                    case R.id.mpuEditarFotoGal:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.mpu_Editar), Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });


        pmPopupMenu.show();
    }

    /*camara empieza*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprovamos que la foto se a realizado
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria
            Bitmap bMap = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory()+
                            "/PrototEmplea/"+"fotoOrg.jpg");
            //Añadimos el bitmap al imageView para
            //mostrarlo por pantalla
            ivFoto.setImageBitmap(bMap);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //External storage activado
                    Toast.makeText(this, R.string.mensaje2_camara, Toast.LENGTH_SHORT).show();

                } else {
                    //¿sin permiso external storage?
                    Toast.makeText(this, R.string.mensaje3_camara, Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //camara activado
                    Toast.makeText(this, R.string.mensaje4_camara, Toast.LENGTH_SHORT).show();
                } else {
                    //¿sin permiso camara?
                    Toast.makeText(this, R.string.mensaje5_camara, Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void enviarCorreo(View view) {
        String correo = tvCorreo.getText().toString();
        Intent itCorreo = new Intent((Intent.ACTION_SEND));
        itCorreo.setData(Uri.parse("mailto:"));
        itCorreo.putExtra(Intent.EXTRA_EMAIL, correo);
        itCorreo.setType("message/rfc822");
        startActivity(Intent.createChooser(itCorreo, "Correo-e"));
    }
}