package com.projctwash.com.proyek2_carwash.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.projctwash.com.proyek2_carwash.Model.Karyawan;
import com.projctwash.com.proyek2_carwash.Model.Member;
import com.projctwash.com.proyek2_carwash.R;

import java.util.List;

public class RecyclerAdapterMember extends RecyclerView.Adapter<RecyclerAdapterMember.MyViewHolder> {
    private List<Member> mMember;
    private Context mContext;

    public RecyclerAdapterMember(List<Member> mMember, Context mContext) {
        this.mMember = mMember;
        this.mContext = mContext;
    }

    @Override
    public RecyclerAdapterMember.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        RecyclerAdapterMember.MyViewHolder mViewHolder = new RecyclerAdapterMember.MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMember.MyViewHolder holder, int position) {
        Member mmbr = mMember.get(position);

        holder.nama.setText(mmbr.getNama());
        holder.no_telp.setText("Hp : "+mmbr.getNo_telp());
    }

    @Override
    public int getItemCount() {
        return mMember.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nama,no_telp;
        public MyViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txv_namakitmember);
            no_telp = itemView.findViewById(R.id.txv_nohpkitmember);
        }
    }
}
