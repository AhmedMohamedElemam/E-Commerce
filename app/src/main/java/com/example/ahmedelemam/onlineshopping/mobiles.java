package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class mobiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobiles);
//-----------------------------------------------------------------------------------

        ListView list = (ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        adapter.add("Samsung Galaxy S3");
        adapter.add("Iphone S6");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView arg=(TextView)view;
                Intent i=new Intent(mobiles.this,mobile_details.class);
                i.putExtra("ProName",arg.getText().toString());
                startActivity(i);

            }
        });

//-----------------------------------------------------------------------------------
    }
}
