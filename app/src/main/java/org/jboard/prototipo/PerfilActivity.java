package org.jboard.prototipo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import org.jboard.prototipo.Adaptadores.PageAdapter;
import org.jboard.prototipo.Fragments.CurriculumFragment;
import org.jboard.prototipo.Fragments.PerfilFragment;

public class PerfilActivity extends AppCompatActivity {

    //private Toolbar tbToolbar;
    private TabLayout tlTab;
    private ViewPager vpPerfil;

    private ImageView ivFoto;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Toolbar actionBar = (Toolbar) findViewById(R.id.abPerfil);
        setSupportActionBar(actionBar);

        //tbToolbar = (Toolbar) findViewById(R.id.tbToolbar);

        tlTab = (TabLayout) findViewById(R.id.tlTab);
        vpPerfil = (ViewPager) findViewById(R.id.vpPerfil);

        setUpViewPager();

    }

    //para usar el viewpager
    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> arrLstFragment = new ArrayList<>();
        arrLstFragment.add(new PerfilFragment());
        arrLstFragment.add(new CurriculumFragment());
        return arrLstFragment;
    }

    //para usar el viewpager
    private void setUpViewPager() {
        vpPerfil.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tlTab.setupWithViewPager(vpPerfil);
        tlTab.getTabAt(0).setIcon(R.drawable.pelo_mujer_48);
        tlTab.getTabAt(1).setIcon(R.drawable.message_52);

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
                                Environment.getExternalStorageDirectory(), "Tutorialeshtml5");
                        imagesFolder.mkdirs();
                        //añadimos el nombre de la imagen
                        File image = new File(imagesFolder, "foto.jpg");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcion_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mopEditar:
                //refrescar();
                break;
            case R.id.mopEditarFoto:
                //refrescar();
                break;
            case R.id.mopVerCurriculum:
                //refrescar();
                break;

            case R.id.moAcerca:
                mostrarInformativo("acerca");
                break;
            case R.id.moCreditos:
                mostrarInformativo("creditos");
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void mostrarInformativo(String strOpcion){

        Intent intent = new Intent(this, InformativoActivity.class);
        intent.putExtra("texto", strOpcion);
        startActivity(intent);
    }

    /*camara empieza*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Comprovamos que la foto se a realizado
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Creamos un bitmap con la imagen recientemente
            //almacenada en la memoria
            Bitmap bMap = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory()+
                            "/Tutorialeshtml5/"+"foto.jpg");
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


}
