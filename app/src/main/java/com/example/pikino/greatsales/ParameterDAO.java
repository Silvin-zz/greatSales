package com.example.pikino.greatsales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by silviobravocado on 14/08/15.
 */
public class ParameterDAO {

    private SQLiteDatabase db;
    private MyModel         dbHelper;
    private String[]        allColumns = {MyModel.PARAMETER_ID, MyModel.PARAMETER_NAME, MyModel.PARAMETER_VALUE};


    public ParameterDAO(Context context){

        this.dbHelper = new MyModel(context);
    }

    public void open() {

        db            = this.dbHelper.getWritableDatabase();
    }

    public void close() {

        dbHelper.close();
    }


    /**
     * Load Parameter by name
     * @param name
     * @return
     */
    public Parameter loadParameterByName(String name){

        Cursor cursor   = db.query(MyModel.PARAMETER_TABLE, allColumns, MyModel.PARAMETER_NAME + "='" + name + "'", null, null, null, null);
        cursor.moveToFirst();
        Parameter parameter = cursorToParameter(cursor);
        cursor.close();
        return parameter;

    }


    /**
     * Update the parameter
     * @param parameter
     */
    public void updateParameterByName(Parameter parameter){
        ContentValues values = new ContentValues();
        values.put(MyModel.PARAMETER_NAME,  parameter.getName());
        values.put(MyModel.PARAMETER_VALUE, parameter.getParametervalue());
        db.update(MyModel.PARAMETER_TABLE,  values, "id =" + parameter.getId(), null);
    }


    /**
     * Load Parameter Object
     * @param cursor
     * @return
     */
    private Parameter cursorToParameter(Cursor cursor){

        Parameter parameter = new Parameter();
        parameter.setId(cursor.getLong(cursor.getColumnIndex("id")));
        parameter.setName(cursor.getString(cursor.getColumnIndex("name")));
        parameter.setParametervalue(cursor.getString(cursor.getColumnIndex("parametervalue")));
        return parameter;

    }



}
