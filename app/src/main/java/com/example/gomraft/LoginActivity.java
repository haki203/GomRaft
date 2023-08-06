    package com.example.gomraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
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
    Button btnLogin,btnCs;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private String resultCS="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnCs=findViewById(R.id.btnCs);
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
        btnCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showPopup(LoginActivity.this,v);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!resultCS.isEmpty()){
                    signIn();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Bạn chưa chọn cơ sở đào tạo.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Xử lý khi người dùng nhấn nút OK
                                    dialog.dismiss(); // Đóng AlertDialog
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
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
            Log.d("Tag", "dang nhap thanh cong, handleSignInResult:");
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
                        intent.putExtra("cs", resultCS);
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
    private void showPopup(Context context,View v){
        // Tạo PopupWindow
        PopupWindow customPopupWindow = new PopupWindow(context);
        View customView = getLayoutInflater().inflate(R.layout.custom_popup_menu, null);
        customPopupWindow.setContentView(customView);

        // Thiết lập các thuộc tính cho PopupWindow
        customPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        customPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        customPopupWindow.setFocusable(true);

        // Tạo các View trong custom PopupMenu
        TextView optionFPT_HCM = customView.findViewById(R.id.option_fpt_hcm);
        TextView optionFPT_Hanoi = customView.findViewById(R.id.option_fpt_hanoi);
        TextView optionFPT_Cantho = customView.findViewById(R.id.option_fpt_cantho);
        TextView optionFPT_Taynguyen = customView.findViewById(R.id.option_fpt_taynguyen);
        // Thiết lập sự kiện khi người dùng chọn các tùy chọn
        optionFPT_HCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi người dùng chọn tùy chọn FPT Polytechnic Hồ Chí Minh
                resultCS = "FPT Polytechnic Hồ Chí Minh";
                customPopupWindow.dismiss(); // Đóng custom PopupMenu
            }
        });

        optionFPT_Hanoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi người dùng chọn tùy chọn FPT Polytechnic Hà Nội
                resultCS = "FPT Polytechnic Hà Nội";
                customPopupWindow.dismiss(); // Đóng custom PopupMenu
            }
        });
        optionFPT_Cantho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi người dùng chọn tùy chọn FPT Polytechnic Hà Nội
                resultCS = "FPT Polytechnic Cần Thơ";
                customPopupWindow.dismiss(); // Đóng custom PopupMenu
            }
        });
        optionFPT_Taynguyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi người dùng chọn tùy chọn FPT Polytechnic Hà Nội
                resultCS = "FPT Polytechnic Tây Nguyên";
                customPopupWindow.dismiss(); // Đóng custom PopupMenu
            }
        });

        // Hiển thị custom PopupMenu giữa màn hình
        customPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }
}

