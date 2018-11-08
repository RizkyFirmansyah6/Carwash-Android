package com.projctwash.com.proyek2_carwash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projctwash.com.proyek2_carwash.Admin.AdminMainActivity;
import com.projctwash.com.proyek2_carwash.User.MainUserActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnk = findViewById(R.id.btn_k);
        Button btno = findViewById(R.id.btn_o);

        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminMainActivity.class);
                startActivity(i);
            }
        });

        btnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginUserActivity.class);
                startActivity(i);
            }
        });
    }
}
