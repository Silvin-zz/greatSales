package com.example.pikino.greatsales;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pikino on 08/08/15.
 */
public class MyModel extends SQLiteOpenHelper{

    private static final String DATABASE_NAME   = "sales.db";
    private static final int DATABASE_VERSION   = 1;


    public MyModel(Context context) {

        super(context,DATABASE_NAME, null, DATABASE_VERSION );
        Log.d("Database", "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "==============================================================");
        //Creamos la base de datos
        String databaseString = "";
        databaseString = "CREATE TABLE PRODUCT  (id INTEGER primary key autoincrement, title TEXT,     description TEXT,  cost REAL                 ); " +
                         "CREATE TABLE SALE     (id INTEGER primary key autoincrement, subtotal REAL,  tax REAL,          total REAL                ); " +
                         "CREATE TABLE DETAIL   (id INTEGER primary key autoincrement, saleid INTEGER, productid INTEGER, count INTEGER, cost REAL  )";
        db.execSQL(databaseString);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String databaseString = "";
        databaseString        ="DROP IF EXIST TABLE PRODUCT; DROP IF EXIST TABLE SALE; DROP IF EXIST TABLE DETAIL";
        db.execSQL(databaseString);
        onCreate(db);
    }
}
