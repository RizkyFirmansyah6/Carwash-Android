package com.projctwash.com.proyek2_carwash.User;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Model.PostputDellTransaksi;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserKonfirmasiActivity extends AppCompatActivity {

    Intent intt;
    HashMap<String,String> user;
    SessionManagement mSesion;

    private EditText platnmr;
    private TextView kendaraan,hargakendaraan,kondisi,hargakondisi,total;
    private FloatingActionButton btn_back,btn_save;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_konfirmasi);

        intt = getIntent();
//        get sesion
        mSesion = new SessionManagement(this);
        user = mSesion.getUserInformation();

        platnmr = findViewById(R.id.et_kplatnomor);
        kendaraan = findViewById(R.id.tv_kkendaraan);
        hargakendaraan = findViewById(R.id.tv_khargakendaraan);
        kondisi = findViewById(R.id.tv_kkondisi);
        hargakondisi = findViewById(R.id.tv_khargakondisi);
        total = findViewById(R.id.tv_ktotal);
//        btn_back = findViewById(R.id.btn_backk);
        btn_save = findViewById(R.id.btn_savekonfirmasi);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        final Integer tot = Integer.parseInt(intt.getStringExtra("hargakendaraan"))+Integer.parseInt(intt.getStringExtra("hargakondisi"));
        kendaraan.setText(intt.getStringExtra("kendaraan"));
        hargakendaraan.setText(intt.getStringExtra("hargakendaraan"));
        kondisi.setText(intt.getStringExtra("kondisi"));
        hargakondisi.setText(intt.getStringExtra("hargakondisi"));

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        total.setText("Total     "+formatRupiah.format(tot));

//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostputDellTransaksi> newKar = mApiInterface.postTransaksi(
                        platnmr.getText().toString(),
                        intt.getStringExtra("kendaraan"),
                        intt.getStringExtra("hargakendaraan"),
                        intt.getStringExtra("kondisi"),
                        intt.getStringExtra("hargakondisi"),
                        tot.toString(),user.get(SessionManagement.KEY_ID)
                         );
                newKar.enqueue(new Callback<PostputDellTransaksi>() {
                    @Override
                    public void onResponse(Call<PostputDellTransaksi> call, Response<PostputDellTransaksi> response) {
                        Toast.makeText(getApplicationContext(),"Suksess Ditambah",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostputDellTransaksi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
