package com.projctwash.com.proyek2_carwash.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.projctwash.com.proyek2_carwash.Config.SessionManagement;
import com.projctwash.com.proyek2_carwash.R;

import java.util.HashMap;


public class SettingAdmin extends Fragment {
    View v;
    CardView btn_exit,btn_event;
    //    sesion management
    HashMap<String,String> user;
    SessionManagement mSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_setting_admin, container, false);

        init();
        return v;
    }

    private void init(){
        //        get sesion
        mSesion = new SessionManagement(getContext());
        user = mSesion.getUserInformation();

        btn_exit =v.findViewById(R.id.btn_logout);
        btn_event= v.findViewById(R.id.btn_event_manager);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSesion.logoutUser();
                getActivity().finish();
            }
        });

        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container_admin,new Event_manager()).addToBackStack(null).commit();
            }
        });

    }
}
