    package com.example.gomraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gomraft.ITF.ApiService;
import com.example.gomraft.Model.User;
import com.example.gomraft.Service.ApiManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


    public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        // login gg
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("Tag", "loi ket noi");
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(i);
//                finish();
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Đăng nhập thành công, lấy thông tin tài khoản
            GoogleSignInAccount account = result.getSignInAccount();
            String displayName = account.getDisplayName();
            String email = account.getEmail();
            Log.d("Tag", "dang nhap thanh cong, gmail: "+email);
            Log.d("Tag", "dang ket noi api login");
            //goi api login r chuyen man hinh
            ApiService apiService = ApiManager.getApiService();
            // Tạo JsonObject để truyền vào email
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", email);
            // Gọi API login
            Call<JsonObject> call = apiService.loginUser(jsonObject);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    // Xử lý kết quả trả về từ API
                    if (response.isSuccessful()) {
                        JsonObject data = response.body();
                        Log.d("API Response", "Response body: " + data.toString());
                        // Xử lý kết quả trả về từ API
                        Gson gson = new Gson();
                        User user = gson.fromJson(data.get("user"), User.class);
                        // Tạo Intent để chuyển từ LoginActivity qua HomeActivity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        // Truyền đối tượng User vào Intent với tên "user"
                        intent.putExtra("user", user);
                        // Chuyển sang HomeActivity
                        startActivity(intent);
                        // Đóng LoginActivity
                        finish();
                        // ...
                    } else {
                        // Xử lý khi API trả về không thành công
                        Log.d("API Response", "Error: " + response.code() + " " + response.message());
                    }
                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // Xử lý lỗi khi không kết nối được với API
                }
            });
            // ...
        } else {
            Log.d("Tag", "dang nhap ko thanh cong");
        }
    }

}

