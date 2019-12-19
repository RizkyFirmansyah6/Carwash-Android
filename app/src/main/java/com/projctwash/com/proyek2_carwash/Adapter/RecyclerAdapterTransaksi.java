package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projctwash.com.proyek2_carwash.Model.Transaksi;
import com.projctwash.com.proyek2_carwash.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapterTransaksi extends RecyclerView.Adapter<RecyclerAdapterTransaksi.MyViewHolder> {

    private List<Transaksi> mTransaksi;
    private Context mContext;

    public RecyclerAdapterTransaksi(List<Transaksi> mTransaksi, Context mContext) {
        this.mTransaksi = mTransaksi;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Transaksi mtran= mTransaksi.get(position);

        holder.nama.setText(mtran.getKendaraan()+" ("+mtran.getNopol()+")");
        holder.tanggal.setText("tanggal : "+mtran.getTanggal());
//        rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.harga.setText("Harga : "+formatRupiah.format(Integer.parseInt(mtran.getTotal())));
    }

    @Override
    public int getItemCount() {return mTransaksi.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,tanggal,harga;
        public MyViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_tnamaken);
            tanggal = itemView.findViewById(R.id.tv_ttanggal);
            harga = itemView.findViewById(R.id.tv_tharga);
        }
    }
}
