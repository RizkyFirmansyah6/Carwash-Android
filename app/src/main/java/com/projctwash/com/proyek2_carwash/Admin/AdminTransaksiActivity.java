package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterTransaksi;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.LoginActivity;
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

public class AdminTransaksiActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi);

//      findtext
        tv_tot = findViewById(R.id.tv_total_transaksi);
//      get sesion
        mSesion = new SessionManagement(this);
        user = mSesion.getUserInformation();
//      spinner
        mySpiner = findViewById(R.id.spinner_admtransaksi);
        myAdpterspin = ArrayAdapter.createFromResource(this,R.array.sortby,R.layout.support_simple_spinner_dropdown_item);
        myAdpterspin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mySpiner.setAdapter(myAdpterspin);

        mySpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initialize(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getApplicationContext(),"Sort by "+adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        find recycler
        mRecyclerView = findViewById(R.id.rcycler_admTransaksi);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        initialize(mySpiner.getSelectedItem().toString());
        initNavigation();

    }

    public void initialize(String sortby){
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
                mAdapter = new RecyclerAdapterTransaksi(mtra,getApplicationContext());
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

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSesion.isLoggedIn()){
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
            finish();
        }
        initialize(mySpiner.getSelectedItem().toString());

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
