package com.example.ahmedelemam.onlineshopping;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class mobile_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_details);


        TextView name=(TextView)findViewById(R.id.textView9);
        TextView price=(TextView)findViewById(R.id.textView12);
        TextView quantity=(TextView)findViewById(R.id.textView13);

        final DBHelper obj =new DBHelper(this);
        obj.create_new_product();

        int ProID =obj.get_pro_id(getIntent().getExtras().getString("ProName"));

        Cursor cursor = obj.get_pro_data(ProID);

        name.setText("Name: "+getIntent().getExtras().getString("ProName"));
        price.setText("Price: "+cursor.getString(0));
        quantity.setText("Quantity: "+cursor.getString(1));
    }
}
