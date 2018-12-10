package com.projctwash.com.proyek2_carwash.Admin.cud;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.projctwash.com.proyek2_carwash.R;

public class new_event extends Fragment {

    View v;
    FloatingActionButton btn_addphoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_new_event, container, false);

        init_listener();
        return v;
    }

    void init_listener(){
        btn_addphoto = v.findViewById(R.id.btn_addphotoevent_admin);
        btn_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // conf menampilkan dialog
                final Dialog dialog_link = new Dialog(getActivity());
                dialog_link.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_link.setContentView(R.layout.new_link_photo);

                dialog_link.show();
            }
        });
    }
}
