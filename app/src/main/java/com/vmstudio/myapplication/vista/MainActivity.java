package com.vmstudio.myapplication.vista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vmstudio.myapplication.R;
import com.vmstudio.myapplication.controlador.sharedP.conexionSharedPreferences;
import com.vmstudio.myapplication.controlador.utileria;
import com.vmstudio.myapplication.controlador.webservices;
import com.vmstudio.myapplication.menu_principal;


public class MainActivity extends AppCompatActivity {

    //componentes de la interfaz
    TextInputLayout tilUsuario,tilContrasena;
    TextInputEditText tietUsuario, tietContrasena;
    Button btnIngresar;

    //instancias
    utileria u;
    conexionSharedPreferences sh;
    webservices ws;

    // variables locales
    String usu,cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Noil_notificaciones);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        init();
    }

    private void instancias() {
        u=new utileria();
        sh=new conexionSharedPreferences();
        ws=new webservices();
    }

    private void init(){
        tilUsuario=findViewById(R.id.TILUsuario);
        tietUsuario=findViewById(R.id.TIETUsuario);
        tilContrasena=findViewById(R.id.TILContraena);
        tietContrasena=findViewById(R.id.TIETContrasena);
        btnIngresar=findViewById(R.id.btnIngresarLogin);

        usu=u.lTIET(tietUsuario);
        cont=u.lTIET(tietContrasena);
    }


    public void onclicBtnIngresar(View v){
       // if(validaUsuario()==true && ValidaContrasena()==true){
          // consulta();
       // }
      //TODO 13/11/2013 CREAR CONSULTA PARA REALIZAR LA VALIDACION CORRECTA
        Intent i=new Intent(MainActivity.this, menu_principal.class);
        startActivity(i);


    }

    private void consulta() {
    /*    String url=u.CreaUrl(MainActivity.this,"Url",ws.LOGIN);

        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       //acciones del resultado
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                        //Fallo en el response
            }
        });

        queue.add(stringRequest);*/

    }

    private boolean ValidaContrasena() {
        boolean contbien=false;
        if(cont.isEmpty()){
            tilContrasena.setError("Campo vacio");
        }else {
            tilContrasena.setError(null);
            contbien=true;
        }
        return contbien;
    }

    private boolean validaUsuario() {
        boolean usubien=false;
        if(usu.isEmpty()){
            tilUsuario.setError("Campo vacio");
        }else {
            tilUsuario.setError(null);
            usubien=true;
        }
        return usubien;
    }

    public void btnIrVistaConexion(View v){

        AlertDialog.Builder builder=new AlertDialog.Builder(this, R.style.FondoDialog);
        LayoutInflater inflater = getLayoutInflater();
        View vistaDialog=inflater.inflate(R.layout.activity_dialog_contrasena,null);
        builder.setView(vistaDialog);

        final AlertDialog dialog= builder.create();
        dialog.show();
        dialog.setCancelable(false);

        CardView btnCancelar= vistaDialog.findViewById(R.id.btnCancelarDialogContrasena);
        CardView btnIngresar= vistaDialog.findViewById(R.id.btnIngresarDialogContrasena);
        EditText etContrasena= vistaDialog.findViewById(R.id.etContrasenadialog);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String  pass =  etContrasena.getText().toString().trim();
              //  if (pass.equals("dsai21")){
                    Intent verDatosConexion=new Intent(getApplicationContext(), datos_conexion.class);
                    startActivity(verDatosConexion);
                    dialog.dismiss();
                //}else{
                  //  Toast.makeText(getApplicationContext(),R.string.Contrasenaincorrecta,Toast.LENGTH_SHORT).show();
               // }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private  String creaUrl(){
        String url="";

        return url;
    }






}