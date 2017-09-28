package com.example.ahmedelemam.onlineshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        final EditText user_security = (EditText) findViewById(R.id.editText3);
        final TextView user_password = (TextView) findViewById(R.id.textView8);
        final Button submit = (Button) findViewById(R.id.button6);
        final DBHelper obj = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String security = user_security.getText().toString();
                String password = obj.return_password(security);
                user_password.setText(password);
            }
            
        });

    }
}




