package com.example.pikino.greatsales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pikino on 08/08/15.
 */
public class ProductDAO {

    private SQLiteDatabase  db;
    private MyModel         dbHelper;
    private String[]        allColumns = {"id", "title", "description", "cost"};


    public ProductDAO(Context context) {
        this.dbHelper = new MyModel(context);
    }

    public void open() {
        db            = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Product createProduct(String title, String description, Float cost){
        ContentValues contentValues     = new ContentValues();

        contentValues.put("title"       , title);
        contentValues.put("description" , description);
        contentValues.put("cost", cost);

        long id         = db.insert("PRODUCT", null, contentValues);
        Cursor cursor   = db.query("PRODUCT", allColumns, "id=" + id, null, null, null, null);
        cursor.moveToFirst();
        Product product = cursorToProduct(cursor);
        cursor.close();
        return product;
    }



    public List<Product> getAllProducts(){

        List<Product>  productList  = new ArrayList<Product>();
        Cursor cursor               = db.query("PRODUCT", allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            Product product = this.cursorToProduct(cursor);
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();

        return productList;

    }




    private Product cursorToProduct(Cursor cursor){

        Product product = new Product();
        product.setId(cursor.getLong(cursor.getColumnIndex("id")));
        product.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        product.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        product.setCost(cursor.getFloat(cursor.getColumnIndex("id")));

        return product;

    }


}
