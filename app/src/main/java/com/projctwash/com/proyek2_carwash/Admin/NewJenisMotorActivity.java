package com.projctwash.com.proyek2_carwash.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewJenisMotorActivity extends AppCompatActivity {

    EditText et_link,nama,harga;
    FloatingActionButton btn_back,btn_new;
    Button btn_cek;
    ImageView img;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jenis_motor);

        et_link = findViewById(R.id.et_linkphotojenismotor);
        nama = findViewById(R.id.et_namajenismotor);
        harga = findViewById(R.id.et_hargajenismotor);
        img = findViewById(R.id.img_editjenismotor);

        btn_cek = findViewById(R.id.btn_cek);
        btn_back = findViewById(R.id.btn_back_ed);
        btn_new = findViewById(R.id.btn_updatejenismotor);


        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btn_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext()).asBitmap().load(et_link.getText().toString()).into(img);
                Toast.makeText(getApplicationContext(),"Checking",Toast.LENGTH_SHORT).show();
            }
        });

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDellKendaraan> newJnsKendaraan = mApiInterface.postKendaraan(
                        nama.getText().toString(),
                        harga.getText().toString(),
                        et_link.getText().toString()
                );
                newJnsKendaraan.enqueue(new Callback<PostPutDellKendaraan>() {
                    @Override
                    public void onResponse(Call<PostPutDellKendaraan> call, Response<PostPutDellKendaraan> response) {
                        Toast.makeText(getApplicationContext(),"Suksess",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellKendaraan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
