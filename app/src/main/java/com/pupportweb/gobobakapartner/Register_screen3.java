package com.pupportweb.gobobakapartner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register_screen3 extends AppCompatActivity {

    private static final int REQUEST_LOCATION = 1;

    private EditText city,address;
    private Button register3_next;
    String sellername,selleremail,sellermobile,sellerpass,sellerstoreName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen3);

        setid();
        onclick();
        Intent intent = getIntent();
        if (intent.hasExtra("name") &&
                intent.hasExtra("email") &&
                intent.hasExtra("mobile") )
        {

            if (!intent.getStringExtra("name").equals(null)) {
                sellername = intent.getStringExtra("name");
                selleremail = intent.getStringExtra("email");
                sellermobile = intent.getStringExtra("mobile");
                sellerpass = intent.getStringExtra("password");
                sellerstoreName = intent.getStringExtra("storeName");
            }
        }
    }

    public void setid() {
        city = findViewById(R.id.et_register_city);
        address = findViewById(R.id.et_register_address);
        register3_next = findViewById(R.id.register3_next);
    }

    public void onclick() {

        register3_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });

    }
    private void clickLogin() {

        if(address.getText().toString().equals("") )
        {
            address.setError("Please Enter Your Address");
        }
        else if(city.getText().toString().equals("") )
        {
            city.setError("Please Enter City Name");
        }
        else
        {
            Intent intent = new Intent(Register_screen3.this, Register_screen4.class);
            intent.putExtra("name", sellername);
            intent.putExtra("email", selleremail);
            intent.putExtra("mobile", sellermobile);
            intent.putExtra("password", sellerpass);
            intent.putExtra("city", city.getText().toString());
            intent.putExtra("address", address.getText().toString());
            intent.putExtra("storeName",sellerstoreName);

            startActivity(intent);
            finish();
        }

    }
}