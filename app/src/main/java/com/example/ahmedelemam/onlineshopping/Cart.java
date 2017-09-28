package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);



        final TextView total = (TextView) findViewById(R.id.textView18);
        final Button make_order = (Button) findViewById(R.id.button9);


        final ListView list =(ListView)findViewById(R.id.listcart);
        final ArrayList<String> al = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,al);
        list.setAdapter(adapter);

        final DBHelper obj = new DBHelper(this);
        final String cust_name = getIntent().getExtras().getString("CustName");

        final ArrayList<String> CartList = getIntent().getExtras().getStringArrayList("CartProducts");
        float price = 0;
        for(int i=0;i<CartList.size();i++)
        {
            adapter.add(CartList.get(i));
            price += Float.parseFloat(obj.get_pro_price(CartList.get(i)));
        }

        total.setText(String.valueOf(price));
//-----------------------------------------------------------------------------------
        make_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Cart.this,Order.class);
                i.putExtra("TotalPrice",total.getText().toString());
                i.putExtra("CustName",cust_name);

                startActivity(i);

            }
        });

//-----------------------------------------------------------------------------------

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView arg=(TextView)view;
                float pro_price = Float.parseFloat(obj.get_pro_price(arg.getText().toString()));
                float total_price = Float.parseFloat(total.getText().toString());
                float update_total_price = total_price - pro_price ;
                total.setText(String.valueOf(update_total_price));
                al.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),((TextView)view).getText().toString()+" Deleted",Toast.LENGTH_LONG).show();

                return true;
            }
        });



//-----------------------------------------------------------------------------------

    }
}
