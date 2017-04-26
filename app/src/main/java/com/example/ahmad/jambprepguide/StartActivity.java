package com.example.ahmad.jambprepguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    Button phone_sign, phone_reg, jamb_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        phone_sign  = (Button) findViewById(R.id.btn_phone_signin);
        phone_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        phone_reg = (Button) findViewById(R.id.phonereg);
        phone_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RegActivity.class));
            }
        });

        jamb_reg = (Button) findViewById(R.id.jambreg);
        jamb_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RegActivity.class));
            }
        });
    }
}
