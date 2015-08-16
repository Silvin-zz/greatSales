package com.example.pikino.greatsales;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyModel extends SQLiteOpenHelper{

    private static final String DATABASE_NAME           = "greatesale.db";
    private static final int DATABASE_VERSION           = 9;


    //PRODUCT

    public static final String PRODUCT_TABLE           =  "PRODUCT";
    public static final String PRODUCT_ID              =  "id";
    public static final String PRODUCT_TITLE           =  "title";
    public static final String PRODUCT_DESCRIPTION     =  "description";
    public static final String PRODUCT_COST            =  "cost";


    //PARAMETER

    public static final String PARAMETER_TABLE         =  "PARAMETER";
    public static final String PARAMETER_ID            =  "id";
    public static final String PARAMETER_NAME          =  "name";
    public static final String PARAMETER_VALUE         =  "parametervalue";
    public static final String PARAMETER_REST_SOURCE   =  "http://ec2-54-187-142-119.us-west-2.compute.amazonaws.com:8080";



    //parameter creation

    private static final String CREATE_PARAMETER       = "create table "
            + PARAMETER_TABLE                          + "("
            + PARAMETER_ID                             + " integer primary key autoincrement, "
            + PARAMETER_NAME                           + " text not null, "
            + PARAMETER_VALUE                          + " text not null"
            + ");";


    //Firts parameter for product rest source
    private static final String DEFAULT_PARAMETER      = "insert into "
            + PARAMETER_TABLE                          + "("
            + PARAMETER_NAME                           + ", "
            + PARAMETER_VALUE                          + ") VALUES ("
            + "'rest_source'"                          + ",'"
            + PARAMETER_REST_SOURCE                    + "');";


    //product creation
    private static final String CREATE_PRODUCT         = "create table "
            + PRODUCT_TABLE                            + "("
            + PRODUCT_ID                               + " integer primary key autoincrement, "
            + PRODUCT_TITLE                            + " text not null, "
            + PRODUCT_DESCRIPTION                      + " text not null, "
            + PRODUCT_COST                             + " real"
            + ");";




    public MyModel(Context context) {

        super(context,DATABASE_NAME, null, DATABASE_VERSION );
        Log.d("Database", "======= We going to create the database");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "======= We going to create the tables for the database");
        //Creating the database
        db.execSQL(this.CREATE_PRODUCT);
        db.execSQL(this.CREATE_PARAMETER);
        db.execSQL(this.DEFAULT_PARAMETER);


        Log.d("parametros", this.CREATE_PRODUCT);
        Log.d("parametros", this.CREATE_PARAMETER);
        Log.d("parametros", this.DEFAULT_PARAMETER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Database", "======= Drop table because the version was change =======");
        db.execSQL("DROP TABLE IF EXISTS " + this.PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + this.PARAMETER_TABLE);
        onCreate(db);
    }
}
