package com.example.gomraft.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomraft.Model.ThongTin;
import com.example.gomraft.R;

import java.util.List;

public class ThongTinAdapter extends RecyclerView.Adapter<ThongTinAdapter.ThongTinViewHolder>{

    private List<ThongTin> mTT;

    public ThongTinAdapter(List<ThongTin> mList) {
        this.mTT = mList;
    }

    @NonNull
    @Override
    public ThongTinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongtin, parent, false);
        return new ThongTinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinViewHolder holder, int position) {
        ThongTin thongTin = mTT.get(position);
        if (thongTin == null){
            return;
        }
        holder.imageView.setImageResource(thongTin.getResourceId());
        holder.textView.setText(thongTin.getName());
        holder.tv_content.setText(thongTin.getContent());
        holder.tv_mota.setText(thongTin.getMota());

    }

    @Override
    public int getItemCount() {
        if (mTT != null){
            return mTT.size();
        }
        return 0;
    }

    public class ThongTinViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView, tv_content, tv_mota;
        public ThongTinViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_user);
            textView = itemView.findViewById(R.id.tv_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_mota = itemView.findViewById(R.id.tv_mota);
        }
    }
}
