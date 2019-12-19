package com.projctwash.com.proyek2_carwash.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
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
    ApiInterface mApiInterface;

    public EditText platnmr;
    public TextView kendaraan,hargakendaraan,kondisi,hargakondisi,total,totalbayar,disc_1,disc_2,nm_evnt;
    private FloatingActionButton btn_back,btn_save;
    Integer tot,tot_semua,disc=0;
    NumberFormat formatRupiah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_konfirmasi);

        intt = getIntent();
//        get sesion
        mSesion = new SessionManagement(this);
        user = mSesion.getUserInformation();
//      init rupiah
        Locale localeID = new Locale("in", "ID");
        formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        initdata_lay();
        hitung();

        getSupportFragmentManager().beginTransaction().replace(R.id.frament_eventcontainer_user,new User_Event()).commit();

        init_listener();

    }

    public void hitung(){
        kendaraan.setText(intt.getStringExtra("kendaraan"));
        hargakendaraan.setText(intt.getStringExtra("hargakendaraan"));
        kondisi.setText(intt.getStringExtra("kondisi"));
        hargakondisi.setText(intt.getStringExtra("hargakondisi"));

        disc_1.setText("-("+disc+"%)");

        tot = Integer.parseInt(intt.getStringExtra("hargakendaraan"))+Integer.parseInt(intt.getStringExtra("hargakondisi"));
        total.setText("Total     "+formatRupiah.format(tot));
        tot_semua = tot-(disc*tot/100);
        disc_2.setText("Disc    - ("+formatRupiah.format((disc*tot/100))+")");

        totalbayar.setText("Total Bayar    "+formatRupiah.format(tot_semua));
    }

    private  void  initdata_lay(){
        platnmr = findViewById(R.id.et_kplatnomor);
        kendaraan = findViewById(R.id.tv_kkendaraan);
        hargakendaraan = findViewById(R.id.tv_khargakendaraan);
        kondisi = findViewById(R.id.tv_kkondisi);
        hargakondisi = findViewById(R.id.tv_khargakondisi);
        total = findViewById(R.id.tv_ktotal);
        totalbayar = findViewById(R.id.totBayar);
        disc_1 = findViewById(R.id.tv_disc1);
        disc_2 = findViewById(R.id.tv_disc2);
        nm_evnt = findViewById(R.id.tv_nmkonfevent_user);
        btn_save = findViewById(R.id.btn_savekonfirmasi);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    private void init_listener(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog process = new ProgressDialog(UserKonfirmasiActivity.this);
                process.setTitle("Processing");
                process.setMessage("Please Wait..");
                process.setCancelable(false);
                process.show();

                Call<PostputDellTransaksi> newKar = mApiInterface.postTransaksi(
                        platnmr.getText().toString(),
                        intt.getStringExtra("kendaraan"),
                        intt.getStringExtra("hargakendaraan"),
                        intt.getStringExtra("kondisi"),
                        intt.getStringExtra("hargakondisi"),
                        tot_semua.toString(),user.get(SessionManagement.KEY_ID)
                );
                newKar.enqueue(new Callback<PostputDellTransaksi>() {
                    @Override
                    public void onResponse(Call<PostputDellTransaksi> call, Response<PostputDellTransaksi> response) {
                        Toast.makeText(getApplicationContext(),"Suksess Ditambah",Toast.LENGTH_SHORT).show();
                        finish();
                        process.hide();
                    }

                    @Override
                    public void onFailure(Call<PostputDellTransaksi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                        process.hide();
                    }
                });

            }
        });

    }
}
