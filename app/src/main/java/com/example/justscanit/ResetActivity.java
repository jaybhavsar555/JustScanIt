package com.example.justscanit;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {
    EditText edtEmail;
     Button btnResetPassword;
     Button btnBack;
     FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        edtEmail = findViewById(R.id.edt_reset_email);
        btnResetPassword =  findViewById(R.id.btn_reset_password);
        btnBack =  findViewById(R.id.btn_back);

        mAuth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();

           if (TextUtils.isEmpty(email)) {
             Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
             return;
          }
          mAuth.sendPasswordResetEmail(email)
                  .addOnCompleteListener(task -> {
                     if (task.isSuccessful()) {
                          Toast.makeText(ResetActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();


                     } else {
                          Toast.makeText(ResetActivity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                     }
                 });
        });

        btnBack.setOnClickListener(v -> finish());

    }
}