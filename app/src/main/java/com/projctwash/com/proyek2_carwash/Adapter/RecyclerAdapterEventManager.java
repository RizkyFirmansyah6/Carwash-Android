package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.projctwash.com.proyek2_carwash.Model.Event;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellEvent;
import com.projctwash.com.proyek2_carwash.Model.PostPutDellKendaraan;
import com.projctwash.com.proyek2_carwash.R;
import com.projctwash.com.proyek2_carwash.Rest.ApiClient;
import com.projctwash.com.proyek2_carwash.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterEventManager extends RecyclerView.Adapter<RecyclerAdapterEventManager.myViewHolder> {

    List<Event> mEvent ;
    Context mCon;

    public RecyclerAdapterEventManager(List<Event> mEvent, Context mCon) {
        this.mEvent = mEvent;
        this.mCon = mCon;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_admin, parent, false);
        myViewHolder mViewHolder = new myViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        final ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        final Event evnt = mEvent.get(position);

        Picasso.get()
                .load(""+evnt.getImg())
//              .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.img_poster);

        holder.tx_nama.setText(evnt.getNama_event());
        holder.tx_tgl.setText(evnt.getBulan());

        if (evnt.getStatus().equals("Aktif")){
            holder.status.setChecked(true);
            holder.status.setText("Status : Aktif");
        }else {
            holder.status.setChecked(false);
            holder.status.setText("Status : nonAktif");
        }

        holder.status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton compoundButton, boolean b) {
                if (b){
                    final AlertDialog.Builder alertDlg = new AlertDialog.Builder(mCon);
                    alertDlg.setMessage("Apa anda yakin '"+evnt.getNama_event()+"', akan di aktifkan ?");
                    alertDlg.setCancelable(false);
                    alertDlg.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Call<PostPutDellEvent> gantistatus = mApiInterface.putStatusEvent(evnt.getId(),"Aktif");
                            gantistatus.enqueue(new Callback<PostPutDellEvent>() {
                                @Override
                                public void onResponse(Call<PostPutDellEvent> call, Response<PostPutDellEvent> response) {
                                    Toast.makeText(mCon,"Set aktif "+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                                    holder.status.setText("Status : Aktif");
                                }

                                @Override
                                public void onFailure(Call<PostPutDellEvent> call, Throwable t) {
                                    Toast.makeText(mCon,"Failur on "+t.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                            alertDlg.create().hide();
                        }
                    });
                    alertDlg.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDlg.create().hide();

                        }
                    });
                    alertDlg.create().show();

                }else {
                    final AlertDialog.Builder alertDlgnon = new AlertDialog.Builder(mCon);
                    alertDlgnon.setMessage("Apa anda yakin '"+evnt.getNama_event()+"', akan di Non aktif ?");
                    alertDlgnon.setCancelable(false);
                    alertDlgnon.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Call<PostPutDellEvent> gantistatus = mApiInterface.putStatusEvent(evnt.getId(),"nonAktif");
                            gantistatus.enqueue(new Callback<PostPutDellEvent>() {
                                @Override
                                public void onResponse(Call<PostPutDellEvent> call, Response<PostPutDellEvent> response) {
                                    Toast.makeText(mCon,"Set Non aktif"+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                                    holder.status.setText("Status : nonAktif");
                                }
                                @Override
                                public void onFailure(Call<PostPutDellEvent> call, Throwable t) {
                                    Toast.makeText(mCon,"Failur on "+t.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                            alertDlgnon.create().hide();
                        }
                    });
                    alertDlgnon.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDlgnon.create().hide();

                        }
                    });
                    alertDlgnon.create().show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvent.size();
    }


    // view holder
    public static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img_poster;
        TextView tx_nama,tx_tgl;
        Switch status;

        public myViewHolder(View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.poster_ivent_user);
            tx_nama = itemView.findViewById(R.id.tv_nama_ivent_user);
            tx_tgl = itemView.findViewById(R.id.tv_tglsam_evnt);
            status = itemView.findViewById(R.id.switch_status);
        }
    }


}