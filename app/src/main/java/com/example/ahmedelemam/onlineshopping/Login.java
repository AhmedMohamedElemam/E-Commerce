package com.example.ahmedelemam.onlineshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    public static  final String Prefs_Name = "MyPrefsFile";
    private static  final String Prefs_UserName = "UserName";
    private static  final String Prefs_Password = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//-----------------------------------------------------------------------------------
        Button login = (Button) findViewById(R.id.button);
        Button sign = (Button) findViewById(R.id.button2);
        Button forget = (Button) findViewById(R.id.button3);
        final CheckBox remember = (CheckBox) findViewById(R.id.checkBox);
        final EditText user = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText2);
        final DBHelper obj = new DBHelper(this);
//-----------------------------------------------------------------------------------
        SharedPreferences pref = getSharedPreferences(Prefs_Name,MODE_PRIVATE);
        String pref_user = pref.getString(Prefs_UserName,null);
        String pref_pass = pref.getString(Prefs_Password,null);
        if(user.getText().toString().equals("")||password.getText().toString().equals(""))
        {
            user.setText(pref_user);
            password.setText(pref_pass);
        }

//-----------------------------------------------------------------------------------
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = user.getText().toString();
                String user_password = password.getText().toString();

                String password_valid = obj.check_password(user_name);

                if(user_password.equals(password_valid))
                {
                    if(remember.isChecked())
                    {
                        getSharedPreferences(Prefs_Name,MODE_PRIVATE)
                                .edit()
                                .putString(Prefs_UserName,user_name)
                                .putString(Prefs_Password,user_password)
                                .commit();
                    }
                    Toast.makeText(getApplicationContext(),"Successful Login Welcome : "+user_name,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Login.this,Categories.class);
                    i.putExtra("CustName",user_name);

                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"UserName & Password Don't Match ",Toast.LENGTH_LONG).show();
                }



            }
        });
//***********************************************************************************
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });
 //***********************************************************************************
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(Login.this,ForgetPassword.class);
                startActivity(i);

            }
        });

//***********************************************************************************
    }
}
