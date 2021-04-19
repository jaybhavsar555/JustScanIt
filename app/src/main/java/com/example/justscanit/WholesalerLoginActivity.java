package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

public class WholesalerLoginActivity extends AppCompatActivity {
TextView txtRegister;
Button btnLogin;
    EditText Emailid,Password;
    TextView txtReset;
    FirebaseAuth mFireBaseAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_login);
        txtRegister=findViewById(R.id.register);
        btnLogin=findViewById(R.id.btnLogin);
        Emailid=findViewById(R.id.edtEmailID);
        Password=findViewById(R.id.edtPassword);
        txtReset=findViewById(R.id.TxtReset);
        mFireBaseAuth= getInstance();
        user=mFireBaseAuth.getCurrentUser();

        btnLogin.setOnClickListener(v -> {

            String email=Emailid.getText().toString();
            String pwd=Password.getText().toString();
            if (email == null || email.isEmpty()){
                Emailid.setError("Required!");
            }
            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                Emailid.setError("Invalid Email Address");
                Emailid.requestFocus();
            }
            else if (pwd == null || pwd.isEmpty()){
                Password.setError("Required!");
            }else{
                getInstance().signInWithEmailAndPassword(email,pwd).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        if (!Objects.requireNonNull(getInstance().getCurrentUser()).isEmailVerified()){
                            Toast.makeText(WholesalerLoginActivity.this, "PLease Verify Your Email!", Toast.LENGTH_SHORT).show();


                        }
                        else {
                            Toast.makeText(WholesalerLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intToHome = new Intent(WholesalerLoginActivity.this,MainActivitytp.class);
                            startActivity(intToHome);
                        }
                    }
                });
            }

        });
        txtRegister.setOnClickListener(v -> startActivity(new Intent(WholesalerLoginActivity.this,WholesalerRegisterActivity.class)));
        txtReset.setOnClickListener((View v) -> {
            Intent intToResetActivity = new Intent(WholesalerLoginActivity.this,ResetActivity.class);
            startActivity(intToResetActivity);

        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        //This code is for auto login if user is not logged off
        if (getInstance().getCurrentUser() != null && getInstance().getCurrentUser().isEmailVerified()){
            Toast.makeText(WholesalerLoginActivity.this, "Auto Login!", Toast.LENGTH_SHORT).show();
            Intent intToHome = new Intent(WholesalerLoginActivity.this,MainActivitytp.class);
            startActivity(intToHome);
        }
    }
}