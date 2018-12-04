package com.projctwash.com.proyek2_carwash.User;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterEvent;
import com.projctwash.com.proyek2_carwash.Adapter.RecyclerAdapterJenisMotor;
import com.projctwash.com.proyek2_carwash.Listener.ClickListener;
import com.projctwash.com.proyek2_carwash.Listener.RecyclerTouchListener;
import com.projctwash.com.proyek2_carwash.Model.Event;
import com.projctwash.com.proyek2_carwash.Model.EventCheckUser;
import com.projctwash.com.proyek2_carwash.Model.GetEvent;
import com.projctwash.com.proyek2_carwash.Model.GetKendaraan;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Event extends Fragment {
    View v ;
    List<Event> mEvent;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterEvent mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user_event, container, false);

        RecyclerEvent();
        return v;
    }

    private void RecyclerEvent(){
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mRecyclerView = v.findViewById(R.id.recycler_event_user);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        Call<GetEvent> getEvnt = mApiInterface.getEventUser();
        getEvnt.enqueue(new Callback<GetEvent>() {
            @Override
            public void onResponse(Call<GetEvent> call, Response<GetEvent> response) {
                mEvent =response.body().getListDataEvent();
                mAdapter = new RecyclerAdapterEvent(mEvent,getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetEvent> call, Throwable t) {

            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int posi) {
                final UserKonfirmasiActivity actvty = (UserKonfirmasiActivity) getActivity();
                if (!actvty.platnmr.getText().toString().trim().equals("")){
                    /// event class
                    final Event  ev = mEvent.get(posi);

                    Call<EventCheckUser> checkStatus = mApiInterface.getEventUserCheck(actvty.platnmr.getText().toString(),ev.getBulan());
                    checkStatus.enqueue(new Callback<EventCheckUser>() {
                        @Override
                        public void onResponse(Call<EventCheckUser> call, final Response<EventCheckUser> response) {
                            final Integer statusMtr = Integer.parseInt(response.body().getJumlah());

                            final Dialog dialog_detail = new Dialog(getActivity());
                            dialog_detail.setContentView(R.layout.event_chek);
                            // init layout
                            ImageView poster = dialog_detail.findViewById(R.id.post_detail_ivent_user);
                            TextView nama = dialog_detail.findViewById(R.id.tv_nama_iventdetail_user);
                            TextView detail = dialog_detail.findViewById(R.id.tv_detail_iventdetail_user);
                            TextView status = dialog_detail.findViewById(R.id.tv_status_iventdetail_user);
                            Button btn_tukar = dialog_detail.findViewById(R.id.btn_exchange_user);

                            nama.setText(ev.getNama_event());
                            detail.setText(ev.getDetail());
                            status.setText(""+statusMtr+"X Cuci");

                            btn_tukar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (statusMtr>=Integer.parseInt(ev.getRequired())){
                                        actvty.nm_evnt.setText(ev.getNama_event());
                                        actvty.disc = Integer.parseInt(ev.getDiskon());
                                        actvty.hitung();

                                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                                        alertDlg.setMessage("Sukses Menukarkan :)");
                                        alertDlg.show();
                                        dialog_detail.hide();
                                    }else {
                                        Integer kurang = Integer.parseInt(ev.getRequired()) - statusMtr;
                                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                                        alertDlg.setMessage("Tingkatkan Terus, cuci anda kurang "+kurang+"X Lagi untuk mendapatkan promo ini");
                                        alertDlg.show();
                                    }
                                }
                            });

                            dialog_detail.show();
                        }

                        @Override
                        public void onFailure(Call<EventCheckUser> call, Throwable t) {
                            Toast.makeText(getContext(),"Something Worng "+t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(getContext(),"Isi Nomor polisi terlebihdahulu :)",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLongClick(View view, int posi) {
            }
        }));
    }

}
