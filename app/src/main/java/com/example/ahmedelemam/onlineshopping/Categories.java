package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

//-----------------------------------------------------------------------------------

        final Button mobiles = (Button) findViewById(R.id.button4);
        final Button laptops = (Button) findViewById(R.id.button7);
        final Button search = (Button) findViewById(R.id.button8);
        final Button cart = (Button) findViewById(R.id.button11);
        final EditText pro_name = (EditText)findViewById(R.id.editText9);

        final ListView list =(ListView)findViewById(R.id.listviewsearch);
        final ArrayList<String> al = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);

        final DBHelper obj = new DBHelper(this);
        final String cust_name = getIntent().getExtras().getString("CustName");


//-----------------------------------------------------------------------------------

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter.clear();
                Cursor cursor = obj.search_pro(pro_name.getText().toString());
                int count=cursor.getCount();
                if(count==0)
                    adapter.clear();

                else {
                    while (!cursor.isAfterLast()) {
                        adapter.add(cursor.getString(0));
                        cursor.moveToNext();
                    }
                }


            }
        });


//-----------------------------------------------------------------------------------
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView arg=(TextView)view;
                al.add(arg.getText().toString());
                Toast.makeText(getApplicationContext(),((TextView)view).getText().toString()+" Added To Cart",Toast.LENGTH_LONG).show();
                return true;
            }
        });

//-----------------------------------------------------------------------------------
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView arg=(TextView)view;
                String price = obj.get_pro_price(arg.getText().toString());
                Toast.makeText(getApplicationContext(),price + " $ ",Toast.LENGTH_LONG).show();

            }
        });

//-----------------------------------------------------------------------------------
        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Categories.this,mobiles.class);
                startActivity(i);

            }
        });
//-----------------------------------------------------------------------------------
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Categories.this,Cart.class);
                i.putExtra("CartProducts",al);
                i.putExtra("CustName",cust_name);
                startActivity(i);

            }
        });
//***********************************************************************************
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Categories.this,laptops.class);
                startActivity(i);
            }
        });
//***********************************************************************************


    }
}
