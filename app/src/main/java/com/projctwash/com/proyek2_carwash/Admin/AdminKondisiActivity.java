package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.projctwash.com.proyek2_carwash.R;

public class AdminKondisiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kondisi);
    }

    public void initNavigation(){
        ImageView btn_karyawan,btn_kendaraan,btn_transaksi,btn_asetting;

        btn_kendaraan = findViewById(R.id.btn_kendaraan);
        btn_karyawan = findViewById(R.id.btn_karyawan);
        btn_asetting = findViewById(R.id.btn_settingadmin);
        btn_transaksi =  findViewById(R.id.btn_aTransaksi);

        btn_kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminMainActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminTransaksiActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_karyawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminKaryawanActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_asetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminSettingActivity.class);
                startActivity(i);
            }
        });

    }
}
