package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.projctwash.com.proyek2_carwash.Model.Kendaraan;
import com.projctwash.com.proyek2_carwash.R;

import java.util.List;

public class RecyclerAdapterJenisMotor extends RecyclerView.Adapter<RecyclerAdapterJenisMotor.MyViewHolder> {

    private List<Kendaraan> mMotor;
    private Context mContext;

    public RecyclerAdapterJenisMotor(List<Kendaraan> mMotor, Context mContext) {
        this.mMotor = mMotor;
        this.mContext = mContext;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jenis_motor, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        final Kendaraan jMo = mMotor.get(i);

        holder.namajenis.setText(jMo.getNama());
        Glide.with(mContext).asBitmap().load(jMo.getImg()).into(holder.img);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext,"ini : "+jMo.getHarga(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mMotor.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView namajenis;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_jenismotor);
            namajenis = itemView.findViewById(R.id.txv_namajenismotor);
        }
    }
}
