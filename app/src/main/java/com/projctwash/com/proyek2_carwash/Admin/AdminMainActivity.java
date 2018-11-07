package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMainActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterJenisMotor mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static AdminMainActivity admjenisken;

    List<Kendaraan> mJMotor;
    FloatingActionButton btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        admjenisken=this;

        mRecyclerView = findViewById(R.id.rcycler_jenismotor);
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        initialize();
    }

    public void initialize(){
        Call<GetKendaraan> gKen = mApiInterface.getKendaraan();

        gKen.enqueue(new Callback<GetKendaraan>() {
            @Override
            public void onResponse(Call<GetKendaraan> call, Response<GetKendaraan> response) {

                mJMotor = response.body().getListDataKendaraan();
                Log.d("Retrofit Get", "Jumlah data Kontak: " +String.valueOf(mJMotor.size()));

                mAdapter = new RecyclerAdapterJenisMotor(mJMotor,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);

                mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int posi) {
                        Kendaraan kndraan = mJMotor.get(posi);

                        Intent i = new Intent(getApplicationContext(),EditJenisMotorActivity.class);
                        i.putExtra("id",kndraan.getId());
                        i.putExtra("nama",kndraan.getNama());
                        i.putExtra("harga",kndraan.getHarga());
                        i.putExtra("img",kndraan.getImg());
                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, int posi) {

                    }
                }));

            }

            @Override
            public void onFailure(Call<GetKendaraan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
