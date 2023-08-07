package com.example.gomraft;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gomraft.Adapter.ThongTinAdapter;
import com.example.gomraft.dto.DetailReponseDTO;
import com.example.gomraft.dto.ListPostsReponseDTO;
import com.example.gomraft.helpers.IRetrofit;
import com.example.gomraft.helpers.RetrofitHelpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TextView txtTitle, txtCOntent, txtCreat;
    ImageView img;
    IRetrofit iRetrofit;
    List<DetailReponseDTO.PostsReponseDTO> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTitle = findViewById(R.id.title);
        txtCOntent = findViewById(R.id.content);
        txtCreat = findViewById(R.id.creat_at);
        img = findViewById(R.id.img_view);
        Intent intent = getIntent();
        if(intent == null) return;
        String title = intent.getStringExtra("post_title");
        String content = intent.getStringExtra("post_content");
        String creatd_at = intent.getStringExtra("post_created_at");
        String image = intent.getStringExtra("post_image");
        txtTitle.setText("Người đăng: "+String.valueOf(title));
        txtCOntent.setText(String.valueOf(content));
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Chuỗi đầu vào
        // Chuyển chuỗi đầu vào thành đối tượng Date
        Date inputDate = null;
        try {
            inputDate = inputFormat.parse(creatd_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Chuyển đối tượng Date thành chuỗi định dạng ngày/tháng/năm
        String formattedDate = outputFormat.format(inputDate);
        txtCreat.setText("Thời gian: "+ formattedDate);
        Glide
                .with(DetailActivity.this)
                .load(image)
                .centerCrop()
                .into(img);
        iRetrofit = RetrofitHelpers.createService(IRetrofit.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        iRetrofit.getPostDetail().enqueue(detailReponseDTOCallback);
    }

    Callback<DetailReponseDTO> detailReponseDTOCallback = new Callback<DetailReponseDTO>() {
        @Override
        public void onResponse(Call<DetailReponseDTO> call, Response<DetailReponseDTO> response) {
            if (response.isSuccessful()){
                DetailReponseDTO reponseDTO = response.body();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=null;
                list=reponseDTO.getPosts();
                try {
                    date =format.parse(list.get(0).getCreated_at());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                list.get(0).setCreated_at(String.valueOf(date));
            }
        }

        @Override
        public void onFailure(Call<DetailReponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}