package com.projctwash.com.proyek2_carwash;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Admin.cud.NewKaryawanActivity;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKaryawan;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellMember;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMemberActivity extends AppCompatActivity {

    EditText nama,notelp,alamat;
    Spinner tipe;
    FloatingActionButton btn_create,btn_back;
    ApiInterface mApiInterface;
    String strtipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        nama = findViewById(R.id.et_namamember);
        notelp = findViewById(R.id.et_notelpmember);
        alamat = findViewById(R.id.et_alamatmember);
        tipe = findViewById(R.id.sp_tipemember);
        btn_back = findViewById(R.id.btn_backmember);
        btn_create = findViewById(R.id.btn_createmember);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(tipe.getSelectedItem()).equals("Member")){
                    strtipe = "1";
                }else if (String.valueOf(tipe.getSelectedItem()).equals("Premium")){
                    strtipe = "2";
                }
                final ProgressDialog process = new ProgressDialog(NewMemberActivity.this);
                process.setTitle("Processing");
                process.setMessage("Please Wait..");
                process.setCancelable(false);
                process.show();

                Call<PostPutDellMember> newMbr = mApiInterface.postMember(
                        nama.getText().toString(),
                        notelp.getText().toString(),
                        alamat.getText().toString(),
                        strtipe);
                newMbr.enqueue(new Callback<PostPutDellMember>() {
                    @Override
                    public void onResponse(Call<PostPutDellMember> call, Response<PostPutDellMember> response) {
                        Toast.makeText(getApplicationContext(),"Suksess Ditambah",Toast.LENGTH_SHORT).show();
                        finish();
                        process.hide();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellMember> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                        process.hide();
                    }
                });
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
