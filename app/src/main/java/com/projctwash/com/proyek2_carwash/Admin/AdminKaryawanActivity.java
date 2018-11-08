package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterKaryawan;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.Model.GetKaryawan;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Karyawan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminKaryawanActivity extends AppCompatActivity {
//    btn navig
    private ImageView btn_karyawan,btn_kendaraan;
    FloatingActionButton btn_add;
//    itmrcycler
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKaryawan mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    Api rest
    private ApiInterface mApiInterface;
//    list karywan
    List<Karyawan> mkary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_karyawan);
        initNavigation();


        btn_add = findViewById(R.id.btn_addkaryawan);

        mRecyclerView = findViewById(R.id.rcycler_karyawan);
        mLayoutManager = new LinearLayoutManager(this,LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        initialize();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NewKaryawanActivity.class);
                startActivity(i);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Karyawan krywn = mkary.get(posi);
                Intent i = new Intent(getApplicationContext(),EditKaryawanActivity.class);
                i.putExtra("id",krywn.getId());
                i.putExtra("nama",krywn.getNama());
                i.putExtra("nohp",krywn.getNohp());
                i.putExtra("alamat",krywn.getAlamat());
                i.putExtra("password",krywn.getPassword());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int posi) {

            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
    }

    public void initialize(){
        Call<GetKaryawan> getKar = mApiInterface.getKaryawan();
        getKar.enqueue(new Callback<GetKaryawan>() {
            @Override
            public void onResponse(Call<GetKaryawan> call, Response<GetKaryawan> response) {
                mkary = response.body().getListDataKaryawan();
                mAdapter = new RecyclerAdapterKaryawan(mkary,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);

                Log.d("Retrofit Get", "Jumlah data Kontak: " +String.valueOf(mkary.size()));
            }

            @Override
            public void onFailure(Call<GetKaryawan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void initNavigation(){
        btn_kendaraan = findViewById(R.id.btn_kendaraan);
        btn_karyawan = findViewById(R.id.btn_karyawan);

        btn_kendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminMainActivity.class);
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

    }
}
