package com.projctwash.com.proyek2_carwash.User;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterKondisi;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.Model.GetKondisi;
import com.projctwash.com.proyek2_carwash.Model.Kondisi;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserKondisiActivity extends AppCompatActivity {

    private Intent intnt;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKondisi mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Kondisi> mKondisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_kondisi);

        intnt = getIntent();
        mRecyclerView = findViewById(R.id.recycler_pilihkondisii);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        initialize();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Kondisi kndraan = mKondisi.get(posi);
                Intent i = new Intent(getApplicationContext(),UserKonfirmasiActivity.class);
                i.putExtra("kendaraan",intnt.getStringExtra("kendaraan"));
                i.putExtra("hargakendaraan",intnt.getStringExtra("harga"));
                i.putExtra("kondisi",kndraan.getNama());
                i.putExtra("hargakondisi",kndraan.getHarga());
                startActivity(i);
                finish();
            }
            @Override
            public void onLongClick(View view, int posi) {
                Kondisi kndraan = mKondisi.get(posi);
                Toast.makeText(getApplicationContext(),"harga : "+kndraan.getHarga(),Toast.LENGTH_SHORT).show();
            }
        }));
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
}
