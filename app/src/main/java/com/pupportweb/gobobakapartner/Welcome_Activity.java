package com.pupportweb.gobobakapartner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pupportweb.gobobakapartner.Dashboard.LoginActivity;

public class Welcome_Activity extends AppCompatActivity {
Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);

        login=findViewById(R.id.btn_welcome_login);
        register=findViewById(R.id.btn_welcome_register);
        OnClick();
    }
    public void OnClick()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startmain = new Intent(Welcome_Activity.this, LoginActivity.class);
                startActivity(startmain);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startmain = new Intent(Welcome_Activity.this, RegisterActivity.class);
                startActivity(startmain);
            }
        });
    }
}