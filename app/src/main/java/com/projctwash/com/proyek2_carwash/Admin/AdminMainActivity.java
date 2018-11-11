package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.LoginActivity;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;
import com.projctwash.com.proyek2_carwash.User.MainUserActivity;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMainActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterJenisMotor mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Kendaraan> mMotor;
    private FloatingActionButton btn_add;
    private Context mcon;

    //    sesion management
    HashMap<String,String> user;
    SessionManagement mSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        mcon = this;

        btn_add = findViewById(R.id.btn_addmotor);
        mRecyclerView = findViewById(R.id.rcycler_jenismotor);
        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        mSesion = new SessionManagement(getApplicationContext());
        user = mSesion.getLevelInformation();

        initNavigation();
        initialize();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NewJenisMotorActivity.class);
                startActivity(i);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Kendaraan kndraan = mMotor.get(posi);
                Toast.makeText(getApplicationContext(),"harga : "+kndraan.getHarga(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int posi) {
                Kendaraan kndraan = mMotor.get(posi);
                Intent i = new Intent(getApplicationContext(),EditJenisMotorActivity.class);
                i.putExtra("id",kndraan.getId());
                i.putExtra("nama",kndraan.getNama());
                i.putExtra("harga",kndraan.getHarga());
                i.putExtra("img",kndraan.getImg());
                startActivity(i);
            }
        }));
    }


    public void initialize(){
        Call<GetKendaraan> gKen = mApiInterface.getKendaraan();
        gKen.enqueue(new Callback<GetKendaraan>() {
            @Override
            public void onResponse(Call<GetKendaraan> call, Response<GetKendaraan> response) {
                List<Kendaraan> mJMotor = response.body().getListDataKendaraan();
                mMotor =mJMotor;
                Log.d("Retrofit Get", "Jumlah data Kontak: " +String.valueOf(mJMotor.size()));

                mAdapter = new RecyclerAdapterJenisMotor(mJMotor,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<GetKendaraan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();

        if (!mSesion.isLoggedIn()){
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            Toast.makeText(getApplicationContext(),"logout berhasil",Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();
        }
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
