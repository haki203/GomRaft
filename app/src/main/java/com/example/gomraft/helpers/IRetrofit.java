package com.example.gomraft.helpers;

import com.example.gomraft.dto.LoginRequestDTO;
import com.example.gomraft.dto.LoginResponseDTO;
import com.example.gomraft.dto.RegisterRequestDTO;
import com.example.gomraft.dto.RegisterResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofit {
    @POST("/api/register.php")
    Call<RegisterResponseDTO> register(@Body RegisterRequestDTO body);

    @POST("/api/login.php")
    Call<LoginResponseDTO> login(@Body LoginRequestDTO body);
}
