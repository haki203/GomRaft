package com.example.gomraft.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gomraft.Model.ThongTin;
import com.example.gomraft.R;
import com.example.gomraft.dto.ListPostsReponseDTO;

import java.util.List;

public class ThongTinAdapter extends RecyclerView.Adapter<ThongTinAdapter.ThongTinViewHolder>{

    private List<ListPostsReponseDTO.PostsReponseDTO> posts;

    public ThongTinAdapter(List<ListPostsReponseDTO.PostsReponseDTO> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ThongTinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongtin, parent, false);
        return new ThongTinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinViewHolder holder, int position) {
        ListPostsReponseDTO.PostsReponseDTO post = posts.get(position);
        if (post == null){
            return;
        }
        holder.textView.setText(post.getTitle());
        holder.tv_content.setText(post.getContent());
        holder.tv_mota.setText(post.getCreated_at());
        Glide
                .with(holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        if (posts != null){
            return posts.size();
        }else {
            return 0;
        }

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
