package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Model.PostPutDellKaryawan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditKaryawanActivity extends AppCompatActivity {

    EditText nama,nohp,alamat,pass;
    FloatingActionButton btn_Update,btn_delete;
    ApiInterface mApiInterface;
    Context contek;
    Intent intn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_karyawan);

//        initial
        intn = getIntent();
        contek = this;
        nama = findViewById(R.id.et_namakaryawan);
        nohp = findViewById(R.id.et_nohpkaryawan);
        alamat = findViewById(R.id.et_alamatkaryawan);
        pass = findViewById(R.id.et_passkaryawan);
        btn_Update = findViewById(R.id.btn_createkaryawan);
        btn_delete = findViewById(R.id.btn_backkaryawan);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
//        initial End
//        Set text
        nama.setText(intn.getStringExtra("nama"));
        nohp.setText(intn.getStringExtra("nohp"));
        alamat.setText(intn.getStringExtra("alamat"));
        pass.setText(intn.getStringExtra("password"));

//      Act btn
        btn_Update.setBackgroundTintList(contek.getResources().getColorStateList(R.color.btnbg_update));
        btn_Update.setImageResource(R.drawable.icbtn_updateprofile);
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDellKaryawan> newKar = mApiInterface.putKaryawan(
                        intn.getStringExtra("id"),
                        nama.getText().toString(),
                        nohp.getText().toString(),
                        alamat.getText().toString(),
                        pass.getText().toString(),"1" );
                newKar.enqueue(new Callback<PostPutDellKaryawan>() {
                    @Override
                    public void onResponse(Call<PostPutDellKaryawan> call, Response<PostPutDellKaryawan> response) {
                        Toast.makeText(getApplicationContext(),"Suksess DiUpdate",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellKaryawan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        btn_delete.setBackgroundTintList(contek.getResources().getColorStateList(R.color.btnbg_delete));
        btn_delete.setImageResource(R.drawable.icbtn_trash);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(contek);
                alertDlg.setMessage("Apa anda yakin '"+intn.getStringExtra("nama")+"', akan dihapus ?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<PostPutDellKaryawan> deletekaryawan = mApiInterface.deleteKaryawan(intn.getStringExtra("id"));
                        deletekaryawan.enqueue(new Callback<PostPutDellKaryawan>() {
                            @Override
                            public void onResponse(Call<PostPutDellKaryawan> call, Response<PostPutDellKaryawan> response) {
                                Toast.makeText(getApplicationContext(), "Sukses terhapus", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<PostPutDellKaryawan> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Error : "+t, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                alertDlg.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDlg.create().show();
            }
        });
    }
}
