package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivitytp extends AppCompatActivity {
Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitytp);
        btnLogout=findViewById(R.id.Logout);
        btnLogout.setOnClickListener(v -> {
          logout();
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intToMain=new Intent(MainActivitytp.this,CustomerLoginActivity.class);
        startActivity(intToMain );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.logoutMenu:{
                logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}