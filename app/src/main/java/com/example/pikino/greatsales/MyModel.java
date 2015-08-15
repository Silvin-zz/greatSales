package com.example.pikino.greatsales;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by pikino on 08/08/15.
 */
public class MyModel extends SQLiteOpenHelper{

    private static final String DATABASE_NAME   = "greatesales.db";
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
        databaseString = "CREATE TABLE PRODUCT      (id INTEGER primary key autoincrement, title TEXT,     description TEXT,  cost REAL                     ); " +
                         "CREATE TABLE SALE         (id INTEGER primary key autoincrement, subtotal REAL,  tax REAL,          total REAL                    ); " +
                         "CREATE TABLE PARAMETER    (id INTEGER primary key autoincrement, name TEXT,      parametervalue TEXT                              ); " +
                         "CREATE TABLE DETAIL       (id INTEGER primary key autoincrement, saleid INTEGER, productid INTEGER, count INTEGER, cost REAL      ); " +
                         "INSERT INTO PARAMETER(name,parametervalue)      VALUES('source', 'http://ec2-54-187-142-119.us-west-2.compute.amazonaws.com:8080' );";
        db.execSQL(databaseString);


        Log.d("parametros", databaseString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Database", "111111111111111111111111111111111111111111111111");
        String databaseString = "";
        databaseString        ="DROP TABLE IF EXISTS PRODUCT; DROP TABLE IF EXISTS SALE; DROP TABLE IF EXISTS DETAIL; DROP TABLE IF EXISTS PARAMETER;";
        db.execSQL(databaseString);
        onCreate(db);
    }
}
