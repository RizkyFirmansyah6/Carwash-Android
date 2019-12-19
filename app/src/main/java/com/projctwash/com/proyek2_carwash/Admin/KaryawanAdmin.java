package com.projctwash.com.proyek2_carwash.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterKaryawan;
import com.projctwash.com.proyek2_carwash.Admin.cud.EditKaryawanActivity;
import com.projctwash.com.proyek2_carwash.Admin.cud.NewKaryawanActivity;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.Model.GetKaryawan;
import com.projctwash.com.proyek2_carwash.Model.Karyawan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KaryawanAdmin extends Fragment {
    View v;
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
    SessionManagement mSesion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       v= inflater.inflate(R.layout.fragment_karyawan_admin, container, false);

       init_view();
       initialize_recycler();
       return v;
    }

    private void init_view(){
        btn_add = v.findViewById(R.id.btn_addkaryawan);
        mSesion = new SessionManagement(getContext());

        mRecyclerView = v.findViewById(R.id.rcycler_karyawan);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),NewKaryawanActivity.class);
                startActivity(i);
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {

            }

            @Override
            public void onLongClick(View view, int posi) {
                Karyawan krywn = mkary.get(posi);
                Intent i = new Intent(getContext(),EditKaryawanActivity.class);
                i.putExtra("id",krywn.getId());
                i.putExtra("nama",krywn.getNama());
                i.putExtra("nohp",krywn.getNohp());
                i.putExtra("alamat",krywn.getAlamat());
                i.putExtra("password",krywn.getPassword());
                startActivity(i);
            }
        }));
    }

    private void initialize_recycler(){
        Call<GetKaryawan> getKar = mApiInterface.getKaryawan();
        getKar.enqueue(new Callback<GetKaryawan>() {
            @Override
            public void onResponse(Call<GetKaryawan> call, Response<GetKaryawan> response) {
                mkary = response.body().getListDataKaryawan();
                mAdapter = new RecyclerAdapterKaryawan(mkary,getContext());
                mRecyclerView.setAdapter(mAdapter);

                Log.d("Retrofit Get", "Jumlah data Kontak: " +String.valueOf(mkary.size()));
            }

            @Override
            public void onFailure(Call<GetKaryawan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
