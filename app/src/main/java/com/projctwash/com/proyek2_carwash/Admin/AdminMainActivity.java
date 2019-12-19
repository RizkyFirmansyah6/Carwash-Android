package com.projctwash.com.proyek2_carwash.Admin;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.BottomNavigationViewHelper;
import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Admin.cud.EditJenisMotorActivity;
import com.projctwash.com.proyek2_carwash.Admin.cud.NewJenisMotorActivity;
import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.LoginActivity;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_admin, new KategoriAdmin()).commit();

        BottomNavigationView botNav = findViewById(R.id.bottom_navigation);
        botNav.setOnNavigationItemSelectedListener(navListener);
        BottomNavigationViewHelper.removeShiftMode(botNav);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedfragment = null; // fragmnet yang akan ditampilkan

                    switch (item.getItemId()){
                        case R.id.nav_kategori_admin:
                            selectedfragment = new KategoriAdmin();
                            break;
                        case R.id.nav_kondisi_admin:
                            selectedfragment = new KondisiAdmin();
                            break;
                        case R.id.nav_transaksi_admin:
                            selectedfragment = new TransaksiAdmin();
                            break;
                        case R.id.nav_karyawan_admin:
                            selectedfragment = new KaryawanAdmin();
                            break;
                        case R.id.nav_setting_admin:
                            selectedfragment = new SettingAdmin();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_admin,selectedfragment).commit();
                    return true;
                }
            };


}
