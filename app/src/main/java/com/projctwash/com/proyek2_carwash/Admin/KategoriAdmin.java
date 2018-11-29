package com.projctwash.com.proyek2_carwash.Admin;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Admin.cud.EditJenisMotorActivity;
import com.projctwash.com.proyek2_carwash.Admin.cud.NewJenisMotorActivity;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriAdmin extends Fragment {

    // init
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
    View v ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kategori_admin, container, false);

        init();
        initialize_recycler();
        return v;
    }


    private void init(){
        mcon = getContext();
        btn_add = v.findViewById(R.id.btn_addmotor);
        mRecyclerView = v.findViewById(R.id.rcycler_jenismotor);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        mSesion = new SessionManagement(getContext());
        user = mSesion.getLevelInformation();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),NewJenisMotorActivity.class);
                startActivity(i);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                Kendaraan kndraan = mMotor.get(posi);
                Toast.makeText(getContext(),"harga : "+kndraan.getHarga(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int posi) {
                Kendaraan kndraan = mMotor.get(posi);
                Intent i = new Intent(getContext(),EditJenisMotorActivity.class);
                i.putExtra("id",kndraan.getId());
                i.putExtra("nama",kndraan.getNama());
                i.putExtra("harga",kndraan.getHarga());
                i.putExtra("img",kndraan.getImg());
                startActivity(i);
            }
        }));
    }

    public void initialize_recycler(){
        Call<GetKendaraan> gKen = mApiInterface.getKendaraan();
        gKen.enqueue(new Callback<GetKendaraan>() {
            @Override
            public void onResponse(Call<GetKendaraan> call, Response<GetKendaraan> response) {
                List<Kendaraan> mJMotor = response.body().getListDataKendaraan();
                mMotor =mJMotor;
                Log.d("Retrofit Get", "Jumlah data Kontak: " +String.valueOf(mJMotor.size()));

                mAdapter = new RecyclerAdapterJenisMotor(mJMotor,getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKendaraan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
