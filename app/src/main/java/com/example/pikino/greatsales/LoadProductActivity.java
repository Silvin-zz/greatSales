package com.example.pikino.greatsales;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;




public class LoadProductActivity extends ActionBarActivity {

    private     String urlProducts;
    private     ParameterDAO parameterSource;
    private     List<Product> lstProducts;
    protected   TextView      txtInfo;
    protected   ProductDAO productDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_product);
        this.parameterSource    = new ParameterDAO(this);
        this.productDAO         = new ProductDAO(this);
        this.txtInfo            = (TextView) this.findViewById(R.id.txtInfo);
        this.loadUrlProducts();
        txtInfo.setText("Buscando productos en :" + this.urlProducts);
        Thread thread           = new Thread(this.runnableProducts);
        txtInfo.setText("Procesando productos .... ");

        try {

            thread.join();
            thread.start();
            txtInfo.setText("¡EnHorabuena! se han procesado todos los productos, ahora ya puedes venderlos");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void loadUrlProducts(){

        this.parameterSource.open();
        Parameter parameter   = this.parameterSource.loadParameterByName("rest_source");
        this.parameterSource.close();
        this.urlProducts = parameter.getParametervalue().toString() + "/api/entity/product";


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void returToPrincipal(View view) {
        this.gotoPrincipal();
    }

    private void gotoPrincipal(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

    private Runnable runnableProducts = new Runnable() {
        @Override
        public void run() {

            lstProducts = new ArrayList<>();
            JSONParser  jParser = new JSONParser();
            JSONArray   json    = jParser.getJSONFromUrl(urlProducts);
            productDAO.open();

            //Clean the table product
            productDAO.clearProduct();
            for( int a = 0; a < json.length(); a++){

                try {
                    Log.d("JSON", json.getJSONObject(a).getString(MyModel.PRODUCT_TITLE));
                    productDAO.createProduct(
                            json.getJSONObject(a).getString(MyModel.PRODUCT_TITLE),
                            json.getJSONObject(a).getString(MyModel.PRODUCT_DESCRIPTION),
                            (float) json.getJSONObject(a).getDouble(MyModel.PRODUCT_COST)
                            );

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            productDAO.close();


        }
    };

}
