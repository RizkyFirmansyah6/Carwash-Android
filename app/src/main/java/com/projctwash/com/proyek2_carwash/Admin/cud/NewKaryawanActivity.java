package com.projctwash.com.proyek2_carwash.Admin.cud;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Model.PostPutDellKaryawan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewKaryawanActivity extends AppCompatActivity {

    EditText nama,nohp,alamat,pass;
    FloatingActionButton btn_create;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_karyawan);

        nama = findViewById(R.id.et_namakaryawan);
        nohp = findViewById(R.id.et_nohpkaryawan);
        alamat = findViewById(R.id.et_alamatkaryawan);
        pass = findViewById(R.id.et_passkaryawan);
        btn_create = findViewById(R.id.btn_createkaryawan);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDellKaryawan> newKar = mApiInterface.postKaryawan(
                                                                                nama.getText().toString(),
                                                                                nohp.getText().toString(),
                                                                                alamat.getText().toString(),
                                                                                pass.getText().toString(),"1" );
                newKar.enqueue(new Callback<PostPutDellKaryawan>() {
                    @Override
                    public void onResponse(Call<PostPutDellKaryawan> call, Response<PostPutDellKaryawan> response) {
                        Toast.makeText(getApplicationContext(),"Suksess Ditambah",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellKaryawan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
