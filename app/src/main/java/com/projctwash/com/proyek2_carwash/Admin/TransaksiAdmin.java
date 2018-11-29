package com.projctwash.com.proyek2_carwash.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterTransaksi;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Model.GetTransaksi;
import com.projctwash.com.proyek2_carwash.Model.Transaksi;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiAdmin extends Fragment {
    View v;
    //  recycler component
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterTransaksi mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Transaksi> mTransaksi;
    //    sesion management
    HashMap<String,String> user;
    SessionManagement mSesion;
    TextView tv_tot;
    //    list spin
    Spinner mySpiner ;
    ArrayAdapter<CharSequence> myAdpterspin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_transaksi_admin, container, false);

        init_view();
        return v;
    }

    private void init_view(){
        //      findtext
        tv_tot = v.findViewById(R.id.tv_total_transaksi);
//      get sesion
        mSesion = new SessionManagement(getContext());
        user = mSesion.getUserInformation();
//      spinner
        mySpiner = v.findViewById(R.id.spinner_admtransaksi);
        myAdpterspin = ArrayAdapter.createFromResource(getContext(),R.array.sortby,R.layout.support_simple_spinner_dropdown_item);
        myAdpterspin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mySpiner.setAdapter(myAdpterspin);

        mySpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initialize(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getContext(),"Sort by "+adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        find recycler
        mRecyclerView = v.findViewById(R.id.rcycler_admTransaksi);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        initialize(mySpiner.getSelectedItem().toString());
    }

    private void initialize(String sortby){
        String qery="";
        if (sortby.equals("Hari ini")){
            qery="today";
        }else if (sortby.equals("minggu ini")){
            qery="week";
        }else if (sortby.equals("Bulan ini")){
            qery="month";
        }

        Call<GetTransaksi> getKOndisi = mApiInterface.getTransaksiBy("",qery);
        getKOndisi.enqueue(new Callback<GetTransaksi>() {
            @Override
            public void onResponse(Call<GetTransaksi> call, Response<GetTransaksi> response) {
                List<Transaksi> mtra = response.body().getListDataTransaksi();
                mTransaksi =mtra;
                mAdapter = new RecyclerAdapterTransaksi(mtra,getContext());
                mRecyclerView.setAdapter(mAdapter);

                Integer total=0;
                for (Transaksi tran: mtra) {
                    total += Integer.parseInt(tran.getTotal());
                }
                Locale localeID = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                tv_tot.setText("Total : "+formatRupiah.format(total));
            }

            @Override
            public void onFailure(Call<GetTransaksi> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}
