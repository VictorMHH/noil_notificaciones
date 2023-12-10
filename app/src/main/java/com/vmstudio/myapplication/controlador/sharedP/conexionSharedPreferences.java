package com.vmstudio.myapplication.controlador.sharedP;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class conexionSharedPreferences {

    public static String leerSharedPreferencesConexion(Context context,String key){

        SharedPreferences preferences = context.getSharedPreferences("UrlConexion", MODE_PRIVATE);
            return  preferences.getString(key, "");
    }

    public static void guardarSharedPreferencesConexion(Context context,String nombreConexion,String url) {
        SharedPreferences preferencias = context.getSharedPreferences("UrlConexion", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("nombreConexion", nombreConexion);
        editor.putString("Url", url);
        editor.commit();
    }



}
