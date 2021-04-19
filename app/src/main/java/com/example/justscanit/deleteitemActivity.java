package com.example.justscanit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deleteitemActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static TextView resultdeleteview;
    FirebaseAuth firebaseAuth;
    Button scantodelete, deletebtn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteitem);
        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("ShopKeeper");
        resultdeleteview = findViewById(R.id.barcodedelete);
        scantodelete = findViewById(R.id.buttonscandelete);
        deletebtn= findViewById(R.id.deleteItemToTheDatabasebtn);

        scantodelete.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ScanCodeActivitydel.class)));

        deletebtn.setOnClickListener(v -> deletefrmdatabase());

    }

    public void deletefrmdatabase()
    {
        String deletebarcodevalue = resultdeleteview.getText().toString();
        FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser=users.getEmail();
        String resultemail = finaluser.replace(".","");
        if(!TextUtils.isEmpty(deletebarcodevalue)){
            databaseReference.child(resultemail).child("Products").child(deletebarcodevalue).removeValue();
            Toast.makeText(this,"Product is Deleted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Please scan Barcode",Toast.LENGTH_SHORT).show();
        }
    }
}