package com.projctwash.com.proyek2_carwash.Admin.cud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;
import com.projctwash.com.proyek2_carwash.User.UserKonfirmasiActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditJenisMotorActivity extends AppCompatActivity {

    Context mcon;
    Intent intn ;
    EditText et_link,nama,harga;
    FloatingActionButton btn_delete,btn_update;
    Button btn_cek;
    ImageView img;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jenis_motor);

        intn = getIntent();
        mcon = this;
        et_link = findViewById(R.id.et_linkphotojenismotor);
        nama = findViewById(R.id.et_namajenismotor);
        harga = findViewById(R.id.et_hargajenismotor);
        img = findViewById(R.id.img_editjenismotor);
        btn_delete = findViewById(R.id.btn_back_ed);
        btn_update = findViewById(R.id.btn_updatejenismotor);
        btn_cek = findViewById(R.id.btn_cek);

        Glide.with(getApplicationContext()).asBitmap().load(intn.getStringExtra("img")).into(img);
        et_link.setText(intn.getStringExtra("img"));
        nama.setText(intn.getStringExtra("nama"));
        harga.setText(intn.getStringExtra("harga"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btn_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext()).asBitmap().load(et_link.getText().toString()).into(img);
                Toast.makeText(getApplicationContext(),"Checking",Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog process = new ProgressDialog(EditJenisMotorActivity.this);
                process.setTitle("Processing");
                process.setMessage("Please Wait..");
                process.setCancelable(false);
                process.show();

                Call<PostPutDellKendaraan> updateJnsKendaraan = mApiInterface.putKendaraan(
                                    intn.getStringExtra("id"),
                                    nama.getText().toString(),
                                    harga.getText().toString(),
                                    et_link.getText().toString()
                            );
                updateJnsKendaraan.enqueue(new Callback<PostPutDellKendaraan>() {
                    @Override
                    public void onResponse(Call<PostPutDellKendaraan> call, Response<PostPutDellKendaraan> response) {
                        Toast.makeText(getApplicationContext(),"Suksess",Toast.LENGTH_SHORT).show();
                        finish();
                        process.hide();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellKendaraan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                        process.hide();
                    }
                });
            }
        });

        btn_delete.setImageResource(R.drawable.icbtn_trash);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(mcon);
                alertDlg.setMessage("Apa anda yakin '"+intn.getStringExtra("nama")+"', akan dihapus ?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final ProgressDialog process = new ProgressDialog(EditJenisMotorActivity.this);
                        process.setTitle("Processing");
                        process.setMessage("Please Wait..");
                        process.setCancelable(false);
                        process.show();

                        Call<PostPutDellKendaraan> deletekendaraan = mApiInterface.deleteKendaraan(intn.getStringExtra("id"));
                        deletekendaraan.enqueue(new Callback<PostPutDellKendaraan>() {
                            @Override
                            public void onResponse(Call<PostPutDellKendaraan> call, Response<PostPutDellKendaraan> response) {
                                finish();
                                process.hide();
                            }

                            @Override
                            public void onFailure(Call<PostPutDellKendaraan> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                process.hide();
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
