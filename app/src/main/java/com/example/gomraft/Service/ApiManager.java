package com.example.gomraft.Service;
import com.example.gomraft.ITF.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "http://10.0.2.2:2345/api/"; // Thay thế bằng URL thực tế của API

    private static Retrofit retrofit;

    public static ApiService getApiService() {
        if (retrofit == null) {
            // Khởi tạo Gson với các tùy chọn nếu cần thiết
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            // Khởi tạo Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        // Trả về đối tượng ApiService đã được tạo
        return retrofit.create(ApiService.class);
    }
}