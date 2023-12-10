package com.vmstudio.myapplication.controlador.dblocal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.vmstudio.myapplication.controlador.dblocal.bdlocalhelper;
import com.vmstudio.myapplication.modelo.conexiones;

import java.util.ArrayList;

public class dbConexiones extends bdlocalhelper {

    Context context;
    public dbConexiones(@Nullable Context context) {
        super(context);

        this.context=context;
    }

    public long insertaRegistroConexion(String nombreConexion, String url){
        long id=0;
        try {
            bdlocalhelper dblocal = new bdlocalhelper(context);
            SQLiteDatabase db = dblocal.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("sNombreConexion", nombreConexion);
            values.put("sUrl", url);

             id = db.insert(TABLE_NOMBRE, null, values);

        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public ArrayList<conexiones> mostrarConexiones(){
        bdlocalhelper dblocal = new bdlocalhelper(context);
        SQLiteDatabase db = dblocal.getWritableDatabase();

        ArrayList<conexiones> listaConexiones=new ArrayList<>();
        conexiones conec=null;
        Cursor cursorConexiones=null;

        cursorConexiones=db.rawQuery("SELECT * FROM "+TABLE_NOMBRE,null);
        if(cursorConexiones.moveToFirst()){
            do{
                conec=new conexiones();
                conec.setIdConexecion(cursorConexiones.getInt(0));
                conec.setNombreServidor(cursorConexiones.getString(1));
                conec.setUrl(cursorConexiones.getString(2));

                listaConexiones.add(conec);
            }while (cursorConexiones.moveToNext());
        }
        cursorConexiones.close();

        return listaConexiones;
    }

    public boolean eliminarConexion(int idConexion){
        Boolean correcto=false;
        dbConexiones dbConexiones=new dbConexiones(context);
        SQLiteDatabase db=dbConexiones.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM "+TABLE_NOMBRE+" WHERE idConexion="+ idConexion);
            correcto=true;
        }catch (Exception e){
            Log.e("E",e.toString());
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }


    public boolean editarConexion(int idConexion,String Nombre, String url){
        Boolean correcto=false;
        dbConexiones dbConexiones=new dbConexiones(context);
        SQLiteDatabase db=dbConexiones.getWritableDatabase();

        try{
            db.execSQL("UPDATE "+TABLE_NOMBRE+" SET sNombreConexion=" +"'"+ Nombre+"'"+ ", sUrl="+"'"+url+"'"+" WHERE idConexion="+idConexion);
            correcto=true;
        }catch (Exception e){
            Log.e("E",e.toString());
            correcto=false;
        }finally {
            db.close();
        }
        return correcto;
    }




}
