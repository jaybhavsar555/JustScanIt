package com.example.justscanit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentGatewayActivity extends AppCompatActivity {
    Button btnUpi,btnCreditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        btnUpi=findViewById(R.id.UPI);
        btnCreditCard=findViewById(R.id.Credit_card);
        btnUpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToUpi=new Intent(PaymentGatewayActivity.this,PhonePeActivity.class);
                startActivity(intentToUpi);
            }
        });
        btnCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToCreditCard=new Intent(PaymentGatewayActivity.this,CardPaymentActivity.class);
                startActivity(intentToCreditCard);
            }
        });
    }
}