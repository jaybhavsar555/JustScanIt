package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(4000);

            }
            catch (Exception e){
                e.printStackTrace();


            }
            finally {
                Intent intent=new Intent(AniActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });
        thread.start();
    }
}