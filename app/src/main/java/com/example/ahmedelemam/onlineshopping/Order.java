package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final Button logout = (Button) findViewById(R.id.button13);
        final Button submit = (Button) findViewById(R.id.button12);

        final EditText address = (EditText) findViewById(R.id.editText7);


        final TextView total = (TextView) findViewById(R.id.textView24);
        final String total_price = getIntent().getExtras().getString("TotalPrice");
        total.setText(total_price);


        final TextView name = (TextView) findViewById(R.id.textView22);
        final String cust_name = getIntent().getExtras().getString("CustName");
        name.setText(cust_name);


        final TextView date = (TextView) findViewById(R.id.textView28);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
        final String order_date = date_format.format(c.getTime());
        date.setText(order_date);


        final DBHelper obj = new DBHelper(this);
        final int cust_id = obj.get_cust_id(cust_name);





        //-----------------------------------------------------------------------------------
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Order.this,Login.class);
                startActivity(i);
                finish();


            }
        });
        //-----------------------------------------------------------------------------------
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String order_address = address.getText().toString();
                obj.create_new_order(order_date,order_address,cust_id);

                Toast.makeText(getApplicationContext()," Done ",Toast.LENGTH_LONG).show();

                name.setText("");
                total.setText("");
                address.setText("");
                date.setText("");


            }
        });
        //-----------------------------------------------------------------------------------


    }
}
