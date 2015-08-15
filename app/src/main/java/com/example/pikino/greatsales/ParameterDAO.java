package com.example.pikino.greatsales;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by silviobravocado on 14/08/15.
 */
public class ParameterDAO {

    private SQLiteDatabase db;
    private MyModel         dbHelper;
    private String[]        allColumns = {"id", "name", "parametervalue"};


    public ParameterDAO(Context context){

        this.dbHelper = new MyModel(context);
    }

    public void open() {

        db            = this.dbHelper.getWritableDatabase();
    }

    public void close() {

        dbHelper.close();
    }

    public Parameter loadParameterByName(String name){

        Cursor cursor   = db.query("PARAMETER", allColumns, "name=" + name + "", null, null, null, null);
        cursor.moveToFirst();
        Parameter parameter = cursorToParameter(cursor);
        cursor.close();
        return parameter;

    }


    private Parameter cursorToParameter(Cursor cursor){

        Parameter parameter = new Parameter();

        parameter.setId(cursor.getLong(cursor.getColumnIndex("id")));
        parameter.setName(cursor.getString(cursor.getColumnIndex("name")));
        parameter.setParametervalue(cursor.getString(cursor.getColumnIndex("parametervalue")));
        return parameter;

    }



}
