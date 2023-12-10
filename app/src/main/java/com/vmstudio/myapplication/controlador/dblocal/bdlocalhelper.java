package com.vmstudio.myapplication.controlador.dblocal;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vmstudio.myapplication.modelo.conexiones;

import java.util.ArrayList;

public class bdlocalhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NOMBRE="configuracioneslocales.db";
    public static final String TABLE_NOMBRE="conexiones";


    public bdlocalhelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NOMBRE+ "(" +
                "idConexion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sNombreConexion VARCHAR(50)," +
                "sUrl VARCHAR(200))");
    }

    //Metodo que se ejecuta cuando se cambia la verison de la de bd
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NOMBRE);
        onCreate(sqLiteDatabase);

    }

}
