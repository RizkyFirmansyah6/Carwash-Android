package com.projctwash.com.proyek2_carwash.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class KondisiAdmin extends Fragment {
    View v;
    private Intent intnt;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterKondisi mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Kondisi> mKondisi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_kondisi_admin, container, false);

        initialize();
        return v;
    }

    public void initialize(){
        mRecyclerView = v.findViewById(R.id.rcycler_admKondisi);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetKondisi> getKOndisi = mApiInterface.getKondisi();
        getKOndisi.enqueue(new Callback<GetKondisi>() {
            @Override
            public void onResponse(Call<GetKondisi> call, Response<GetKondisi> response) {
                List<Kondisi> mKOnd = response.body().getListDataKondisi();
                mKondisi =mKOnd;
                mAdapter = new RecyclerAdapterKondisi(mKOnd,getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKondisi> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }


}
