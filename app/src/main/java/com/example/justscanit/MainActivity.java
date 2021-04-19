package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnCustomer,btnShopkeeper,btnWholesaler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCustomer=findViewById(R.id.BtnCustomer);
        btnShopkeeper=findViewById(R.id.BtnShopekeeper);
        btnWholesaler=findViewById(R.id.BtnWholesaler);
        btnCustomer.setOnClickListener(v -> {
            Intent customerloginintent = new Intent(MainActivity.this,CustomerLoginActivity.class);
            startActivity(customerloginintent);
        });
        btnShopkeeper.setOnClickListener(v -> {
            Intent Shopekeeperloginintent = new Intent(MainActivity.this,ShopekeeperLoginActivity.class);
            startActivity(Shopekeeperloginintent);
        });
        btnWholesaler.setOnClickListener(v -> {
            Intent Wholsalerloginintent = new Intent(MainActivity.this,WholesalerLoginActivity.class);
            startActivity(Wholsalerloginintent);
        });

    }
}