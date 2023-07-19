package com.example.gomraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gomraft.dto.RegisterRequestDTO;
import com.example.gomraft.dto.RegisterResponseDTO;
import com.example.gomraft.helpers.IRetrofit;
import com.example.gomraft.helpers.RetrofitHelpers;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText editName, edtPass, edtEmail;
    Button btnDK;
    IRetrofit iRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        edtEmail = findViewById(R.id.edtMail);
        btnDK = findViewById(R.id.btnDangKy);
        iRetrofit = RetrofitHelpers.createService(IRetrofit.class);
    }
    public void onRegisterClick(View view){
        String name = editName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        // bat loi
        // dung bieu thuc chinh quy
        RegisterRequestDTO requestDTO = new RegisterRequestDTO(email, password, name);
        iRetrofit.register(requestDTO).enqueue(register);
    }

    Callback<RegisterResponseDTO> register = new Callback<RegisterResponseDTO>() {
        @Override
        public void onResponse(Call<RegisterResponseDTO> call, Response<RegisterResponseDTO> response) {
            if (response.isSuccessful()){
                RegisterResponseDTO registerResponse = response.body();
                if(registerResponse.isStatus()){
                    Toast.makeText(RegisterActivity.this,"Đăng Ký thành Công", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<RegisterResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };

}