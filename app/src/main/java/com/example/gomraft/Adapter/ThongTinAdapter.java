package com.example.gomraft.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gomraft.DetailActivity;
import com.example.gomraft.MainActivity;
import com.example.gomraft.Model.ThongTin;
import com.example.gomraft.R;
import com.example.gomraft.dto.ListPostsReponseDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ThongTinAdapter extends RecyclerView.Adapter<ThongTinAdapter.ThongTinViewHolder>{

    private List<ListPostsReponseDTO.PostsReponseDTO> posts;
    private AdapterView.OnItemClickListener listener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

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
        holder.textView.setText("Người đăng: "+post.getTitle());
        holder.tv_content.setText(post.getContent());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Chuỗi đầu vào
        // Chuyển chuỗi đầu vào thành đối tượng Date
        Date inputDate = null;
        try {
            inputDate = inputFormat.parse(post.getCreated_at());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Chuyển đối tượng Date thành chuỗi định dạng ngày/tháng/năm
        String formattedDate = outputFormat.format(inputDate);
        holder.tv_mota.setText("Thời gian: "+ formattedDate);
        Glide
                .with(holder.itemView.getContext())
                .load(post.getImage())
                .centerCrop()
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("post_id", post.getId());
                intent.putExtra("post_image", post.getImage());
                intent.putExtra("post_content", post.getContent());
                intent.putExtra("post_created_at", post.getCreated_at());
                intent.putExtra("post_title", post.getTitle());
                holder.itemView.getContext().startActivity(intent);
            }
        });
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
        private TextView textView, tv_content, tv_mota;
        private ImageView img;
        public ThongTinViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_mota = itemView.findViewById(R.id.tv_mota);
            img = itemView.findViewById(R.id.img_anh);
        }
    }
}
