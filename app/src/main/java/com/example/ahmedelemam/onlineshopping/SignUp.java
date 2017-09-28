package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static java.util.logging.Logger.global;

public class SignUp extends AppCompatActivity {
    public String user_birth;
    public String user_gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//-----------------------------------------------------------------------
        Button submit = (Button) findViewById(R.id.button5);

        final EditText user = (EditText) findViewById(R.id.editText4);
        final EditText password = (EditText) findViewById(R.id.editText5);
        final EditText security = (EditText) findViewById(R.id.editText6);

        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView2);

        final RadioButton male = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton female = (RadioButton) findViewById(R.id.radioButton2);

        final DBHelper obj = new DBHelper(this);

//-----------------------------------------------------------------------
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = user.getText().toString();
                String user_password = password.getText().toString();
                String user_security = security.getText().toString();
                if(male.isChecked()){user_gender="male";}
                else if (female.isChecked()){user_gender="female";}

                obj.create_new_customer(user_name,user_password,user_gender,user_birth,user_security);

                Intent i = new Intent(SignUp.this,Login.class);
                Toast.makeText(getApplicationContext(),"Successful SignUp",Toast.LENGTH_LONG).show();

                startActivity(i);
                finish();

            }
        });
//*************************************************************************
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                user_birth = dayOfMonth +"/"+ month +"/"+ year;
                Toast.makeText(getApplicationContext(),user_birth,Toast.LENGTH_LONG).show();


            }
        });

//*************************************************************************


    }
}
