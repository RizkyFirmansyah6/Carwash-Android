package com.projctwash.com.proyek2_carwash;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Admin.AdminMainActivity;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Model.Karyawan;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKaryawan;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;
import com.projctwash.com.proyek2_carwash.User.MainUserActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton btn_login,btn_exit;
    EditText nohp,pas;
    ApiInterface mApiInterface;
    //    sesion management
    SessionManagement mSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        btn_login = findViewById(R.id.btn_loginkar);
        btn_exit = findViewById(R.id.btn_back);
        nohp = findViewById(R.id.et_nohplogin);
        pas = findViewById(R.id.et_passlogin);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mSesion = new SessionManagement(getApplicationContext());

        if (mSesion.isLoggedIn()){
            HashMap<String,String> user = mSesion.getLevelInformation();

            if (user.get(SessionManagement.KEY_LEVEL).equals("0")){
                Intent i = new Intent(getApplicationContext(),AdminMainActivity.class);
                startActivity(i);
                finish();
            }else if (user.get(SessionManagement.KEY_LEVEL).equals("1")) {
                Intent i = new Intent(getApplicationContext(),MainUserActivity.class);
                startActivity(i);
                finish();
            }
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDellKaryawan> getlogin = mApiInterface.getLogin( nohp.getText().toString(), pas.getText().toString());
                getlogin.enqueue(new Callback<PostPutDellKaryawan>() {
                    @Override
                    public void onResponse(Call<PostPutDellKaryawan> call, Response<PostPutDellKaryawan> response) {
                        String status = response.body().getStatus();
                        if (status.equals("okee")){
                            Karyawan krywn = response.body().getmKaryawan();
//                            create sesion
                            mSesion.createLoginSession(krywn.getId(),krywn.getNama(),krywn.getLevel());

                            if (krywn.getLevel().equals("0")){
                                Intent i = new Intent(getApplicationContext(),AdminMainActivity.class);
                                startActivity(i);
                                finish();
                            }else  {
                                Intent i = new Intent(getApplicationContext(),MainUserActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"fail login",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutDellKaryawan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
