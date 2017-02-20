package org.jboard.prototipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import org.jboard.prototipo.Adaptadores.PageAdapter;
import org.jboard.prototipo.Fragments.DescripcionEditarFragment;
import org.jboard.prototipo.Fragments.DescripcionFragment;
import org.jboard.prototipo.Fragments.PostulantesFragment;

import static org.jboard.prototipo.R.id.ivFoto;

public class DescripcionActivity extends AppCompatActivity {

    //private Toolbar tbToolbar;
    private TabLayout tlTab;
    private ViewPager vpDescripcion;

    //private ImageView ivFoto;

    // private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    //private static final int MY_PERMISSIONS_REQUEST_CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        Toolbar actionBar = (Toolbar) findViewById(R.id.abPerfil);
        setSupportActionBar(actionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //tbToolbar = (Toolbar) findViewById(R.id.tbToolbar);

        tlTab = (TabLayout) findViewById(R.id.tlTab);
        vpDescripcion = (ViewPager) findViewById(R.id.vpDescripcion);

        Bundle parametros = getIntent().getExtras();
        String vacante = parametros.getString("vacante");
        String telefono = parametros.getString("telefono");
        String correo = parametros.getString("correo");
        String descripcion = parametros.getString("descripcion");

        Log.i("vacante", vacante);

        setUpViewPager(vacante, telefono, correo, descripcion);
    }

    //para usar el viewpager
    private ArrayList<Fragment> agregarFragments(String vacante, String telefono, String correo, String descripcion) {
        ArrayList<Fragment> arrLstFragment = new ArrayList<>();
        arrLstFragment.add(DescripcionFragment.newInstance(vacante, telefono, correo, descripcion));
        arrLstFragment.add(new PostulantesFragment());
        arrLstFragment.add(new PostulantesFragment());
        return arrLstFragment;
    }

    //para usar el viewpager
    private void setUpViewPager(String vacante, String telefono, String correo, String descripcion) {
        vpDescripcion.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments(vacante, telefono, correo, descripcion)));
        tlTab.setupWithViewPager(vpDescripcion);
        //tlTab.getTabAt(0).setIcon(R.drawable.ic_profile);
        tlTab.getTabAt(0).setText(R.string.tabVacDesc);
        //tlTab.getTabAt(1).setIcon(R.drawable.ic_curriculum);
        tlTab.getTabAt(1).setText(R.string.tabVacPost);
        ///tlTab.getTabAt(2).setIcon(R.drawable.ic_profile);
        tlTab.getTabAt(2).setText(R.string.tabVacSelec);

    }

    /*public void verMenuPopup(View view) {
        ivFoto = (ImageView) view.findViewById(ivFoto);
        PopupMenu pmPopupMenu = new PopupMenu(this, ivFoto);
        pmPopupMenu.getMenuInflater().inflate(R.menu.menu_popup, pmPopupMenu.getMenu());

        //camara empieza
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

        //camara finaliza

        pmPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mpuEditarFotoCam:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.mpu_Editar), Toast.LENGTH_LONG).show();

                        //camara empieza

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

                        //camara finaliza


                        break;
                    case R.id.mpuEditarFotoGal:
                        Toast.makeText(getBaseContext(), getResources().getString(R.string.mpu_Editar), Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });


        pmPopupMenu.show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcion_descripcion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.modEditar:
                //refrescar();
                break;
            case R.id.modVerPostulantes:
                //refrescar();
                break;
            case R.id.modVerCandidatos:
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
}

