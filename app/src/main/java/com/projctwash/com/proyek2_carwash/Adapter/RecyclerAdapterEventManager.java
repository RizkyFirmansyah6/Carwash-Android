package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.projctwash.com.proyek2_carwash.Model.Event;
import com.projctwash.com.proyek2_carwash.R;

import java.util.List;

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
    public void onBindViewHolder(myViewHolder holder, int position) {
        Event evnt = mEvent.get(position);
//        Glide.with(mCon).asBitmap().load(evnt.getImg()).into(holder.img_poster);
        holder.tx_nama.setText(evnt.getNama_event());
        holder.tx_tgl.setText(evnt.getBulan());
    }

    @Override
    public int getItemCount() {
        return mEvent.size();
    }


    // view holder
    public static class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img_poster;
        TextView tx_nama,tx_tgl;

        public myViewHolder(View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.poster_ivent_user);
            tx_nama = itemView.findViewById(R.id.tv_nama_ivent_user);
            tx_tgl = itemView.findViewById(R.id.tv_tglsam_evnt);
        }
    }


}