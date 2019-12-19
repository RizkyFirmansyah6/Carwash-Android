package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projctwash.com.proyek2_carwash.Model.Karyawan;
import com.projctwash.com.proyek2_carwash.R;

import java.util.List;

public class RecyclerAdapterKaryawan extends RecyclerView.Adapter<RecyclerAdapterKaryawan.MyViewHolder> {

    private List<Karyawan> mKaryawan;
    private Context mContext;

    public RecyclerAdapterKaryawan(List<Karyawan> mKaryawan, Context mContext) {
        this.mKaryawan = mKaryawan;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_karyawan, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Karyawan mkry = mKaryawan.get(position);

        holder.nama.setText(mkry.getNama());
        holder.nohp.setText("Hp : "+mkry.getNohp());
    }

    @Override
    public int getItemCount() {
        return mKaryawan.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,nohp;
        public MyViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txv_namakitm);
            nohp = itemView.findViewById(R.id.txv_nohpkitm);
        }
    }

}
