package com.example.gomraft.helpers;

import com.example.gomraft.dto.RegisterRequestDTO;
import com.example.gomraft.dto.RegisterResponeDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofit {
    @POST("/register.php")
    Call<RegisterResponeDTO> register(@Body RegisterRequestDTO body);
}
