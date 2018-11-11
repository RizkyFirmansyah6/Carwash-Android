package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterKondisi;
import com.projctwash.com.proyek2_carwash.Model.GetKondisi;
import com.projctwash.com.proyek2_carwash.Model.Kondisi;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminKondisiActivity extends AppCompatActivity {

    private Intent intnt;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKondisi mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Kondisi> mKondisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kondisi);
        initNavigation();

        mRecyclerView = findViewById(R.id.rcycler_admKondisi);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        initialize();
    }

    public void initialize(){
        Call<GetKondisi> getKOndisi = mApiInterface.getKondisi();
        getKOndisi.enqueue(new Callback<GetKondisi>() {
            @Override
            public void onResponse(Call<GetKondisi> call, Response<GetKondisi> response) {
                List<Kondisi> mKOnd = response.body().getListDataKondisi();
                mKondisi =mKOnd;
                mAdapter = new RecyclerAdapterKondisi(mKOnd,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKondisi> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void initNavigation(){
        ImageView btn_karyawan,btn_kendaraan,btn_transaksi,btn_asetting,btn_aKondisi;

        btn_kendaraan = findViewById(R.id.btn_kendaraan);
        btn_aKondisi = findViewById(R.id.btn_akondisi);
        btn_transaksi =  findViewById(R.id.btn_aTransaksi);
        btn_karyawan = findViewById(R.id.btn_karyawan);
        btn_asetting = findViewById(R.id.btn_settingadmin);

        btn_kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminMainActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_aKondisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminKondisiActivity.class);
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
