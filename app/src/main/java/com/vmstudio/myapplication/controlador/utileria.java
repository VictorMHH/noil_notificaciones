package com.vmstudio.myapplication.controlador;

import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;
import com.vmstudio.myapplication.controlador.sharedP.conexionSharedPreferences;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class utileria {


    //metodo limpia TextInputEditText (lTIET)  el TextInputEditText y lo retorna como string
    public String lTIET(TextInputEditText text){
        String var="";
        var=text.getText().toString().trim();
        return var;
    }

    //metodo que concatena la url que esta almacenada en el archivo de sharedpreference con
    //las variable de webservices
    public String CreaUrl(Context context,String key,String NombreWebService){
        conexionSharedPreferences sh=new conexionSharedPreferences();
        String url="";
        url=sh.leerSharedPreferencesConexion(context,key).concat(NombreWebService);
       return url;
    }

    //recibe un double y lo conviete a texto
    //revibe entero para definir el numero de decimales a formatear
    public String formatoNumero(double num,int decimales){
        String numeroFormateado;
        String patron="";

        switch (decimales){
            case 0:patron="#,###";
            break;
            case 1:patron="#,###.0";
                break;
            case 2:patron="#,###.00";
                break;
            case 3:patron="#,###.000";
                break;
            case 4:patron="#,###.0000";
                break;
            case 5:patron="#,###.00000";
                break;
            case 6:patron="#,###.000000";
                break;
            default:patron="#,###.#";
        }

        DecimalFormat formato = new DecimalFormat(patron);
        numeroFormateado=formato.format(num);

        return numeroFormateado;
    }




}
