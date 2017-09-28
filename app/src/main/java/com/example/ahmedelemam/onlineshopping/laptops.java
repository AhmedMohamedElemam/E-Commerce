package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class laptops extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptops);
        //-----------------------------------------------------------------------------------
        ListView list = (ListView)findViewById(R.id.listview2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        adapter.add("Hp");
        adapter.add("Dell");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView arg=(TextView)view;
                Intent i=new Intent(laptops.this,laptop_details.class);
                i.putExtra("ProName",arg.getText().toString());
                startActivity(i);

            }
        });

//-----------------------------------------------------------------------------------

    }
}
