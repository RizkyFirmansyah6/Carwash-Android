package com.projctwash.com.proyek2_carwash.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.R;

import java.util.HashMap;

public class UserSettingActivity extends AppCompatActivity {
    Button btn_exit;
    Button btn_member;
    //    sesion management
    HashMap<String,String> user;
    SessionManagement mSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        btn_exit =findViewById(R.id.btn_logout);
        btn_member =findViewById(R.id.btn_member);
        //        get sesion
        mSesion = new SessionManagement(this);
        user = mSesion.getUserInformation();

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSesion.logoutUser();
                finish();
            }
        });

        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserMemberActivity.class));
            }
        });
    }
}
