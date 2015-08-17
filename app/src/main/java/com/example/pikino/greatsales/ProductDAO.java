package com.example.pikino.greatsales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pikino on 08/08/15.
 */
public class ProductDAO {

    private SQLiteDatabase  db;
    private MyModel         dbHelper;
    private String[]        allColumns = {MyModel.PRODUCT_ID, MyModel.PRODUCT_TITLE, MyModel.PRODUCT_DESCRIPTION, MyModel.PRODUCT_COST};


    public ProductDAO(Context context) {
        this.dbHelper = new MyModel(context);
    }

    public void open() {
        db            = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Product createProduct(String title, String description, float cost){
        ContentValues contentValues     = new ContentValues();


        contentValues.put(MyModel.PRODUCT_TITLE,        title);
        contentValues.put(MyModel.PRODUCT_DESCRIPTION,  description);
        contentValues.put(MyModel.PRODUCT_COST,         cost);

        Log.d("Productos", "Agregando el producto :" + title);

        long id         = db.insert("PRODUCT", null, contentValues);
        Cursor cursor   = db.query("PRODUCT", allColumns, "id=" + id, null, null, null, null);
        cursor.moveToFirst();
        Product product = cursorToProduct(cursor);
        cursor.close();
        return product;
    }

    public void clearProduct(){
        db.execSQL("DELETE  FROM " + MyModel.PRODUCT_TABLE);
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
