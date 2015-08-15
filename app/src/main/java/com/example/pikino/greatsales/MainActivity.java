package com.example.pikino.greatsales;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {


    private ProductDAO datasource;
    private ParameterDAO parameterSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource      = new ProductDAO(this);
        parameterSource = new ParameterDAO(this);


    }


    private void loadAllProducts(){
        datasource.open();
        List<Product> productList           = new ArrayList<Product>();
        productList                         = this.datasource.getAllProducts();
        ArrayAdapter<Product> arrayAdapter  = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, productList);
        setListAdapter(arrayAdapter);
        datasource.close();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void loadParameterSource(){

        this.parameterSource.open();
        Parameter parameter = this.parameterSource.loadParameterByName("source");
        Log.d("parametros", "=============================================================");
        Log.d("parametros",parameter.getParametervalue());
        this.parameterSource.close();
    }

    public void getProducts(View view) {
        Log.d("parametros", "LLegamos a parametros =======================================");
        this.loadParameterSource();
        //this.loadAllProducts();
    }


}
