package com.example.gomraft.Helpers;

import com.example.gomraft.Dto.ListScheduleSubjectResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofit {
    @GET("api/get-schedules.php")
//    @Query("user_id") int user_id,
//    @Query("type") int type,
//    @Query("date") int date,
    Call<ListScheduleSubjectResponseDTO> getSchedule(@Query("date") int date,@Query("asc") int asc);
}
