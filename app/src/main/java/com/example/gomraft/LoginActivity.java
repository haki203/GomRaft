package com.example.gomraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gomraft.dto.LoginRequestDTO;
import com.example.gomraft.dto.LoginResponseDTO;
import com.example.gomraft.dto.RegisterRequestDTO;
import com.example.gomraft.dto.RegisterResponseDTO;
import com.example.gomraft.helpers.IRetrofit;
import com.example.gomraft.helpers.RetrofitHelpers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtMail, edtPassword;
    Button btndk, btnLogin;
    TextView textRes;
    IRetrofit iRetrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textRes = findViewById(R.id.txtRes);
        btnLogin = findViewById(R.id.btnLogin);
        edtMail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        iRetrofit = RetrofitHelpers.createService(IRetrofit.class);

    }

    public void onResClick(View view){
        Intent res = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(res);
    }

    public void onLoginClick(View view){
        String email = edtMail.getText().toString();
        String password = edtPassword.getText().toString();
        // bat loi
        // dung bieu thuc chinh quy

        LoginRequestDTO requestDTO = new LoginRequestDTO(email, password);
        iRetrofit.login(requestDTO).enqueue(login);
    }

    Callback<LoginResponseDTO> login = new Callback<LoginResponseDTO>() {
        @Override
        public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
            if (response.isSuccessful()){
                LoginResponseDTO loginResponseDTO = response.body();
                if(loginResponseDTO.isStatus()){
                    Toast.makeText(LoginActivity.this,"Đăng Nhập thành Công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}