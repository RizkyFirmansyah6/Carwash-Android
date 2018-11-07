package com.projctwash.com.proyek2_carwash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Model.JenisMotor;

import java.util.ArrayList;
import java.util.List;

public class JenisMotorActivity extends AppCompatActivity {

    List<JenisMotor> mJMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_motor);

        RecyclerView rvJmtr = findViewById(R.id.rcycler_jenismotor);
        initobj();

        RecyclerAdapterJenisMotor mAdapter = new RecyclerAdapterJenisMotor(mJMotor,this);
        rvJmtr.setLayoutManager(new GridLayoutManager(this,2));
        rvJmtr.setAdapter(mAdapter);
    }

    public void initobj(){
        mJMotor = new ArrayList<>();
        mJMotor.add(new JenisMotor("Bebek",1,7000));
        mJMotor.add(new JenisMotor("Ayam",1,8000));
        mJMotor.add(new JenisMotor("Srigala",1,8000));
        mJMotor.add(new JenisMotor("Bebek",1,7000));
        mJMotor.add(new JenisMotor("Bebek",1,7000));
    }
}
