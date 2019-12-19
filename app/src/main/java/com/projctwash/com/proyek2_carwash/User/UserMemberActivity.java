package com.projctwash.com.proyek2_carwash.User;

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

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterMember;
import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterTransaksi;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.LoginActivity;
import com.projctwash.com.proyek2_carwash.Model.GetMember;
import com.projctwash.com.proyek2_carwash.Model.GetTransaksi;
import com.projctwash.com.proyek2_carwash.Model.Member;
import com.projctwash.com.proyek2_carwash.Model.Transaksi;
import com.projctwash.com.proyek2_carwash.NewMemberActivity;
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

public class UserMemberActivity extends AppCompatActivity {

    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterMember mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Member> mMember;
    HashMap<String,String> user;
    SessionManagement mSesion;

    //    Button
    FloatingActionButton btn_addmember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_member);

        btn_addmember = findViewById(R.id.btn_addmember);
        mSesion = new SessionManagement(this);
        user = mSesion.getUserInformation();

        mRecyclerView = findViewById(R.id.rcycler_member);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        initialize();

        btn_addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewMemberActivity.class));
            }
        });
    }

    public void initialize(){

        Call<GetMember> getMember = mApiInterface.getMember();
        getMember.enqueue(new Callback<GetMember>() {
            @Override
            public void onResponse(Call<GetMember> call, Response<GetMember> response) {
                List<Member> mmbr = response.body().getListDataMember();
                mMember =mmbr;
                mAdapter = new RecyclerAdapterMember(mmbr,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetMember> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mSesion.isLoggedIn()){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }

    }
}
