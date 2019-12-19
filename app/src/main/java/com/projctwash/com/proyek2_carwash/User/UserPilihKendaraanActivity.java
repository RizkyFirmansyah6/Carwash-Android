package com.projctwash.com.proyek2_carwash.User;

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

public class UserPilihKendaraanActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterJenisMotor mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Kendaraan> mMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pilih_kendaraan);

        mRecyclerView = findViewById(R.id.recycler_pilihjenisken);
        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Kendaraan kndraan = mMotor.get(posi);
                Intent i = new Intent(getApplicationContext(),UserKondisiActivity.class);
                i.putExtra("kendaraan",kndraan.getNama());
                i.putExtra("harga",kndraan.getHarga());
                startActivity(i);
                finish();
            }
            @Override
            public void onLongClick(View view, int posi) {
                Kendaraan kndraan = mMotor.get(posi);
                Toast.makeText(getApplicationContext(),"harga : "+kndraan.getHarga(),Toast.LENGTH_SHORT).show();
            }
        }));

    }

    @Override
    protected void onStart() {
        super.onStart();
        initialize();
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
}
