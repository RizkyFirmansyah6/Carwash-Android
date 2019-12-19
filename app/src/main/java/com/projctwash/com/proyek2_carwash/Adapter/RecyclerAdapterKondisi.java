package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.projctwash.com.proyek2_carwash.Model.Kondisi;
import com.projctwash.com.proyek2_carwash.R;

import java.util.List;

public class RecyclerAdapterKondisi extends RecyclerView.Adapter<RecyclerAdapterKondisi.MyViewHolder>{

    private List<Kondisi> mKondisi ;
    private Context mcon;

    public RecyclerAdapterKondisi(List<Kondisi> mKondisi, Context mcon) {
        this.mKondisi = mKondisi;
        this.mcon = mcon;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kondisi, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Kondisi kondsi = mKondisi.get(position);

        holder.namakondisi.setText(kondsi.getNama());
        Glide.with(mcon).asBitmap().load(kondsi.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mKondisi.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView namakondisi;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_kondisimotor);
            namakondisi = itemView.findViewById(R.id.txv_namakondisimotor);
        }
    }
}
