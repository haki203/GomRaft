package com.example.gomraft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText editName, edtPass, edtEmail;
    Button btnDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editName = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        edtEmail = findViewById(R.id.edtMail);
        btnDK = findViewById(R.id.btnDangKy);
        
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}