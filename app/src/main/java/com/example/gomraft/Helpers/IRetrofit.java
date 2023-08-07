package com.example.gomraft.helpers;


import com.example.gomraft.dto.DetailReponseDTO;
import com.example.gomraft.dto.ListPostsReponseDTO;

import retrofit2.Call;
import retrofit2.http.POST;

public interface IRetrofit {

    @POST("/api/get_all_posts.php")
    Call<ListPostsReponseDTO> getAllProducts();
    @POST("/api/get-post-detail.php")
    Call<DetailReponseDTO> getPostDetail();
}
