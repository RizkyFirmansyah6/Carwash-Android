package com.projctwash.com.proyek2_carwash.Admin.cud;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Model.PostPutDellEvent;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class new_event extends AppCompatActivity {

    ApiInterface mApi;
    ImageView img;
    EditText nama,detail,diskon,minimal_cuci,aktifdibulan;
    FloatingActionButton btn_addphoto;
    Button btn_save;
    String link_img="http://";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_event);

        init_layout();
        init_listener();
    }

    private void init_layout(){
        mApi = ApiClient.getClient().create(ApiInterface.class);
        img = findViewById(R.id.image_newevent);
        nama = findViewById(R.id.et_namaevent);
        detail = findViewById(R.id.et_detailevent);
        diskon = findViewById(R.id.et_diskonevent);
        minimal_cuci = findViewById(R.id.et_minimalcuci);
        aktifdibulan = findViewById(R.id.et_aktifbulan);
        btn_save = findViewById(R.id.btn_save);
    }

    void init_listener(){
        btn_addphoto = findViewById(R.id.btn_addphotoevent_admin);
        btn_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // conf menampilkan dialog
                final Dialog dialog_link = new Dialog(new_event.this);
                dialog_link.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_link.setContentView(R.layout.new_link_photo);
                final EditText et_link = dialog_link.findViewById(R.id.et_linkk);
                Button btn = dialog_link.findViewById(R.id.btn_ok);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Picasso.get()
                                .load(et_link.getText().toString())
//                                .placeholder(R.drawable.user_placeholder)
                                .error(R.drawable.ic_error)
                                .into(img);
                        link_img = et_link.getText().toString();
                        dialog_link.hide();
                    }
                });

                dialog_link.show();
            }
        });

        //btn savee
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog process = new ProgressDialog(new_event.this);
                process.setTitle("Processing");
                process.setMessage("Please Wait..");
                process.setCancelable(false);
                process.show();

                Call<PostPutDellEvent> new_event = mApi.newEvent(
                                                    nama.getText().toString(),
                                                    link_img,
                                                    detail.getText().toString(),
                                                    diskon.getText().toString(),
                                                    minimal_cuci.getText().toString(),
                                                    aktifdibulan.getText().toString(),
                                                    "nonAktif");

                new_event.enqueue(new Callback<PostPutDellEvent>() {
                    @Override
                    public void onResponse(Call<PostPutDellEvent> call, Response<PostPutDellEvent> response) {
                        Toast.makeText(getApplicationContext(),"okkk",Toast.LENGTH_SHORT).show();
                        process.hide();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDellEvent> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"failur "+t.getMessage(),Toast.LENGTH_SHORT).show();
                        process.hide();
                        finish();
                    }
                });
            }
        });


    }
}
