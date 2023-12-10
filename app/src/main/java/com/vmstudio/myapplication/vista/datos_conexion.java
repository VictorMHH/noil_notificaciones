package com.vmstudio.myapplication.vista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.controlador.sharedP.conexionSharedPreferences;
import com.vmstudio.myapplication.controlador.dblocal.conexionesAdapter;
import com.vmstudio.myapplication.controlador.dblocal.dbConexiones;
import com.vmstudio.myapplication.controlador.dialogCargando;
import com.vmstudio.myapplication.controlador.utileria;
import com.vmstudio.myapplication.modelo.conexiones;

import java.util.ArrayList;

public class datos_conexion extends AppCompatActivity {

    //componenn del xlm agregar_item_conexiones
    CardView btnCancelar;
    CardView btnGuardar;
    TextInputEditText etNombreServidor;
    TextInputEditText eURL;
    TextInputLayout tilNombreConexion;
    TextInputLayout tilUrl;

    //instancias
    conexionSharedPreferences sh;
    dialogCargando dc;
    dbConexiones dbConexiones;
    conexionesAdapter adapter;
    utileria u;

    //variables de componentes de este activity
    RecyclerView recyclerConexiones;
    ExtendedFloatingActionButton fabExtOpciones;
    FloatingActionButton fabAgregar,fabEditar,fabEliminar;
    TextView tvAgregar,tvEditar,tvEliminar;

    androidx.appcompat.widget.Toolbar toolbar;

    TextView tvNombreconexion, tvUrl;

    //variables globales
    ArrayList<conexiones> listaArrayConexiones;

    private String vNombreconexion;
    private String vUrl;

    boolean FABOpcionesVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_conexion);

        instacias();
        dc.mostrarDialodCargando();
        initComponentes();
        actulizaNombreyUrl();
        funcionRecycler();
        dc.ocultarDialogCargando();
    }

    private void initComponentes(){
        recyclerConexiones=findViewById(R.id.lvListaConexiones);
        fabAgregar=findViewById(R.id.fabAgregarconexion);
        tvNombreconexion=findViewById(R.id.tvDCNombre);
        tvUrl=findViewById(R.id.tvUrl);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void instacias(){
        sh=new conexionSharedPreferences();
        dc=new dialogCargando(datos_conexion.this);
        dbConexiones=new dbConexiones(datos_conexion.this);
        adapter=new conexionesAdapter(dbConexiones.mostrarConexiones());
        u=new utileria();
    }
    private void actulizaNombreyUrl(){
        tvNombreconexion.setText(conexionSharedPreferences.leerSharedPreferencesConexion(this,"nombreConexion"));
        tvUrl.setText(conexionSharedPreferences.leerSharedPreferencesConexion(this,"Url"));
    }

   private void funcionRecycler(){
        recyclerConexiones.setHasFixedSize(false);
        recyclerConexiones.setLayoutManager(new LinearLayoutManager(datos_conexion.this));
        listaArrayConexiones=new ArrayList<>();
        recyclerConexiones.setAdapter(adapter);
        adapter.setOnItemClickListener(new conexionesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               //Toast.makeText(datos_conexion.this,"clic genera en registro"+position+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOpcioneClick(int position) {
                String nombre=dbConexiones.mostrarConexiones().get(position).getNombreServidor();
                String url=dbConexiones.mostrarConexiones().get(position).getUrl();
                int id=dbConexiones.mostrarConexiones().get(position).getIdConexecion();
                mostrarDlgOpciones(nombre,url,id);

            }
        });

    }

    private void mostrarDlgOpciones(String vNombreconexion,String url,int id) {
        CardView definirPricipal, editar, eliminar, cancelar;

        AlertDialog.Builder builder=new AlertDialog.Builder(datos_conexion.this, R.style.FondoDialog);
        LayoutInflater inflater = getLayoutInflater();
        View dlgopciones=inflater.inflate(R.layout.dlg_opciones_detalle_conexion,null);
        builder.setView(dlgopciones);


        definirPricipal= dlgopciones.findViewById(R.id.opc1);
        editar= dlgopciones.findViewById(R.id.opc2);
        eliminar= dlgopciones.findViewById(R.id.opc3);
        cancelar= dlgopciones.findViewById(R.id.opc4);

        final AlertDialog dialogt= builder.create();
        dialogt.show();
        dialogt.setCancelable(false);

        definirPricipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(datos_conexion.this,"op1",Toast.LENGTH_SHORT).show();
               // dialogt.dismiss();
            //    muestraDialogConfirmacion(conexionSharedPreferences.leerSharedPreferencesConexion(getApplicationContext(),"nombreConexion")
              //          , conexionSharedPreferences.leerSharedPreferencesConexion(getApplicationContext(),"Url"));
                muestraDialogConfirmacion(vNombreconexion,url);


            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(datos_conexion.this,"op3",Toast.LENGTH_SHORT).show();
                dialogt.dismiss();
                AlertDialog.Builder builder=new AlertDialog.Builder(datos_conexion.this);
                LayoutInflater inflater = getLayoutInflater();
                View editaconexion=inflater.inflate(R.layout.agregar_item_conexion,null);
                builder.setView(editaconexion);

                btnCancelar=  editaconexion.findViewById(R.id.CVcancelar);
                btnGuardar=   editaconexion.findViewById(R.id.CVguardar);

                //texinputs
                etNombreServidor=   editaconexion.findViewById(R.id.TILETnombreConexion);
                eURL=    editaconexion.findViewById(R.id.TILETurl);

                //textinputslayaut
                tilNombreConexion=  editaconexion.findViewById(R.id.TILnombreConexion);
                tilUrl=   editaconexion.findViewById(R.id.TILurl);

                etNombreServidor.setText(vNombreconexion);
                eURL.setText(url);

                final AlertDialog dialog= builder.create();
                dialog.show();
                dialog.setCancelable(false);

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validaNombreConexion() && validaURL()){
                            if(dbConexiones.editarConexion(id,u.lTIET(etNombreServidor),u.lTIET(eURL))){
                                dialog.dismiss();
                                Toast.makeText(datos_conexion.this,"Edición exitosa",Toast.LENGTH_SHORT).show();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        datos_conexion.this.recreate();
                                    }
                                }, 1500);
                            }else{
                                Toast.makeText(datos_conexion.this,"Error al editar",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });



            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(datos_conexion.this,"op4",Toast.LENGTH_SHORT).show();
                dialogt.dismiss();
                AlertDialog.Builder builder1=new AlertDialog.Builder(datos_conexion.this);
                builder1.setMessage("¿Esta seguro que desea eliminar la Conexió?");
                builder1.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dbConexiones.eliminarConexion(id)){
                            datos_conexion.this.recreate();
                        }else{
                            Toast.makeText(datos_conexion.this,"Eror al eliminar",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(datos_conexion.this,"op5",Toast.LENGTH_SHORT).show();
                dialogt.dismiss();
            }
        });


    }


    //acciones del boton flotante agregar
    public void onclicagregarConexion(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(datos_conexion.this, R.style.FondoDialog);
        LayoutInflater inflater = getLayoutInflater();
        View vistaDialogAgregarDatos=inflater.inflate(R.layout.agregar_item_conexion,null);
        builder.setView(vistaDialogAgregarDatos);

        //botones
        btnCancelar=  vistaDialogAgregarDatos.findViewById(R.id.CVcancelar);
        btnGuardar=   vistaDialogAgregarDatos.findViewById(R.id.CVguardar);

        //texinputs
        etNombreServidor=   vistaDialogAgregarDatos.findViewById(R.id.TILETnombreConexion);
        eURL=    vistaDialogAgregarDatos.findViewById(R.id.TILETurl);

        //textinputslayaut
        tilNombreConexion=  vistaDialogAgregarDatos.findViewById(R.id.TILnombreConexion);
        tilUrl=   vistaDialogAgregarDatos.findViewById(R.id.TILurl);



        final AlertDialog dialog= builder.create();
        dialog.show();
        dialog.setCancelable(true);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaNombreConexion() && validaURL()){
                    if(insertaRegistrosaConexion(u.lTIET(etNombreServidor),u.lTIET(eURL))){
                        Toast.makeText(datos_conexion.this,"Registro Guardado",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                datos_conexion.this.recreate();
                            }
                        }, 1500);

                    }else{
                        Toast.makeText(datos_conexion.this,"Error al guardar Registro",Toast.LENGTH_SHORT).show();
                    }

                   // dialog.dismiss();
                   // datos_conexion.this.recreate();
                }
            }
        });
        //datos_conexion.this.recreate();
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void muestraDialogConfirmacion(String nombreConexion, String url){
        AlertDialog.Builder builder=new AlertDialog.Builder(datos_conexion.this);
        LayoutInflater inflater = getLayoutInflater();
        View vistadialogConfirmacion=inflater.inflate(R.layout.dialog_seleccionar_item,null);
        builder.setView(vistadialogConfirmacion);
        final AlertDialog dialog= builder.create();
        dialog.show();
        dialog.setCancelable(false);
        TextView pregunta= vistadialogConfirmacion.findViewById(R.id.tvConfirmarPregunta);
        CardView btnCancelar= vistadialogConfirmacion.findViewById(R.id.btnCancelarConexion);
        CardView btnAceptar= vistadialogConfirmacion.findViewById(R.id.btnAceptarConexion);

        pregunta.setText("¿Desea definir la conexión: "+nombreConexion+" como principal?");


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardaUrlSharedPreferences(nombreConexion,url);
                actulizaNombreyUrl();
                dialog.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void guardaUrlSharedPreferences(String nombreConexion,String url){
        SharedPreferences preferencias=getSharedPreferences("UrlConexion",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("nombreConexion", nombreConexion);
        editor.putString("Url", url);
        editor.commit();
    }

    private boolean insertaRegistrosaConexion(String nombre, String url) {
        boolean ok=true;
        dbConexiones dbconexiones=new dbConexiones(datos_conexion.this);
        long id=  dbconexiones.insertaRegistroConexion(nombre,url);
        if (id>0){
           // Toast.makeText(this,"Registro Guardado",Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }else{
            //Toast.makeText(this,"Error al guardar Registro",Toast.LENGTH_SHORT).show();
            ok=false;
        }
        return  ok;
    }

    //limpia los campos
    private void limpiarCampos(){
        etNombreServidor.setText("");
        eURL.setText("");
    }


    //valida campo nombreConexion
    private boolean validaNombreConexion(){
        boolean nombreConexionbien=false;
        vNombreconexion=etNombreServidor.getText().toString().trim();

        if (vNombreconexion.isEmpty()){
            tilNombreConexion.setError("Campo vacio");
        }else{
            tilNombreConexion.setError(null);
            nombreConexionbien=true;
        }

        return nombreConexionbien;
    }

    //valida campo servidor
    private boolean validaURL(){
        boolean urlbien=false;
        vUrl=eURL.getText().toString().trim();

        if (vUrl.isEmpty()){
            tilUrl.setError("Campo vacio");
        }else {

            urlbien=true;
        }

        if ( URLUtil.isValidUrl(vUrl)){
            tilUrl.setError(null);
        }else{
            tilUrl.setError("URL no valida");
            urlbien=false;
        }

        return urlbien;
    }




}