package com.pupportweb.gobobakapartner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity {
    Button button_mobile_next;
    EditText text_mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_mobile_next=findViewById(R.id.send_otp);
        text_mob=findViewById(R.id.et_register_mob);

        button_mobile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (text_mob.equals("") || text_mob.length() < 10) {
                    text_mob.setError("Enter valid Mobile No");
                    text_mob.requestFocus();
                    return;
                }
                else {
                    Intent i = new Intent(RegisterActivity.this, Register_Verify_Otp.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("mobile", text_mob.getText().toString());
                    startActivity(i);
                }
            }

        });

    }
}