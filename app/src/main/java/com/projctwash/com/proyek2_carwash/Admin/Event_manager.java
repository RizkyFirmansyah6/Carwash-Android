package com.projctwash.com.proyek2_carwash.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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


public class Event_manager extends AppCompatActivity {

    List<Event> mEvent;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterEventManager mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_manager);

        init_recyclerEvent();
        init_listener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init_recyclerEvent();
    }

    private void init_recyclerEvent() {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mRecyclerView = findViewById(R.id.recycler_eventmanager_admin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Call<GetEvent> getEvnt = mApiInterface.getEventAdmin();
        getEvnt.enqueue(new Callback<GetEvent>() {
            @Override
            public void onResponse(Call<GetEvent> call, Response<GetEvent> response) {
                mEvent = response.body().getListDataEvent();
                mAdapter = new RecyclerAdapterEventManager(mEvent,Event_manager.this );
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetEvent> call, Throwable t) {

            }
        });
    }

    void init_listener(){
        FloatingActionButton btn_add = findViewById(R.id.btn_addevent);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),new_event.class);
                startActivity(i);
            }
        });
    }
}
