package com.projctwash.com.proyek2_carwash.Admin;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterEvent;
import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterEventManager;
import com.projctwash.com.proyek2_carwash.Admin.cud.new_event;
import com.projctwash.com.proyek2_carwash.Model.Event;
import com.projctwash.com.proyek2_carwash.Model.GetEvent;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Event_manager extends Fragment {

    View v;
    List<Event> mEvent;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterEventManager mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_event_manager, container, false);

        init_recyclerEvent();
        init_listener();
        return v;
    }

    private void init_recyclerEvent() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mRecyclerView = v.findViewById(R.id.recycler_eventmanager_admin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        Call<GetEvent> getEvnt = mApiInterface.getEventAdmin();
        getEvnt.enqueue(new Callback<GetEvent>() {
            @Override
            public void onResponse(Call<GetEvent> call, Response<GetEvent> response) {
                mEvent = response.body().getListDataEvent();
                mAdapter = new RecyclerAdapterEventManager(mEvent, getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetEvent> call, Throwable t) {

            }
        });
    }

    void init_listener(){
        FloatingActionButton btn_add = v.findViewById(R.id.btn_addevent);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frament_container_admin,new new_event()).addToBackStack(null).commit();
            }
        });
    }
}
