package com.pupportweb.gobobakapartner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pupportweb.gobobakapartner.util.PasswordValidation;

import java.util.regex.Pattern;

public class Register_screen2 extends AppCompatActivity {
    private static final String TAG = "";
    private EditText name,email,password,storeName,conPassword;
private Button register2_next;
String sellermobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen2);
        setid();
        onclick();

        Intent intent = getIntent();
        if (intent.hasExtra("mobile"))
        {

            if (!intent.getStringExtra("mobile").equals(null)) {
                sellermobile = intent.getStringExtra("mobile");
            }
        }
    }

    public void setid()
    {
        name=findViewById(R.id.et_register_name);
        email=findViewById(R.id.et_register_email);
        password=findViewById(R.id.et_register_pass);
        register2_next=findViewById(R.id.register2_next);
        storeName = findViewById(R.id.et_store_name);
        conPassword=findViewById(R.id.et_register_confirmpass);
    }
    public void onclick()
    {

        register2_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickLogin();
            }
        });
    }

    private void clickLogin() {
        Log.d(TAG, "clickLogin: ");
        //        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
//        startActivity(intent);
        if(name.getText().toString().equals("") )
        {
            name.setError("Enter Your Name");
        }

        else if(email.getText().toString().equals(""))
        {
            email.setError("Enter Valid Email");
        }
        else if(storeName.getText().toString().equals("")){
            storeName.setError("Enter Store Name");
        }
        else if(password.getText().toString().equals("")){
            password.setError("Enter password");
        }
        else if (!Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$").matcher(password.getText().toString().trim()).matches()){
            password.setError("Minimum 8 characters, at least one uppercase, one lowercase, one number and one special character");
        }
        else if(conPassword.getText().toString().equals("")){
            conPassword.setError("Enter Confirm password");
        }
        else if(!conPassword.getText().toString().equals(password.getText().toString())){
            conPassword.setError("Password and Confirm-password do not match");
        }
        else
        {
            Intent intent = new Intent(Register_screen2.this, Register_screen3.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("password", password.getText().toString());
            intent.putExtra("mobile", sellermobile);
            intent.putExtra("storeName",storeName.getText().toString());
            startActivity(intent);
            finish();
        }

    }
}