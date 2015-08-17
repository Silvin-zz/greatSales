package com.example.pikino.greatsales;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class SaleActivity extends ListActivity {

    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        this.productDAO         = new ProductDAO(this);
        this.loadProducts();
    }


    /**
     * Load the products from the database
     */
    private void loadProducts(){

        this.productDAO.open();

        List<Product> tmpProducts     = this.productDAO.getAllProducts();
        ArrayList<String> productList = new ArrayList<>();

        for( int a = 0; a < tmpProducts.size(); a++){

            productList.add(((Product)tmpProducts.get(a)).getTitle());
        }

        this.productDAO.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productList);
        setListAdapter(adapter);




    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sale, menu);
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

    private void gotoPrincipal(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

    public void returToPrincipal(View view) {
        this.gotoPrincipal();
    }
}
