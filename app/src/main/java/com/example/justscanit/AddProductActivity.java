package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductActivity extends AppCompatActivity {
     EditText productname,productcategory,productprice;
     TextView productbarcode;
     FirebaseAuth firebaseAuth;
   static  TextView resulttextview;
    Button scanbutton, additemtodatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferencecat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("ShopKeeper");
        resulttextview = findViewById(R.id.barcodeview);
        additemtodatabase = findViewById(R.id.additembuttontodatabase);
        scanbutton = findViewById(R.id.buttonscan);
        productname = findViewById(R.id.editProductname);
        productcategory= findViewById(R.id.editcategory);
        productprice = findViewById(R.id.editprice);
        productbarcode= findViewById(R.id.barcodeview);

        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivity.class));
            }
        });

        additemtodatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem();
            }
        });

    }

    private void additem() {
        String productameValue = productname.getText().toString();
        String productcategoryValue = productcategory.getText().toString();
        String productpriceValue = productprice.getText().toString();
        String productbarcodeValue = productbarcode.getText().toString();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser=users.getEmail();
        String resultemail = finaluser.replace(".","");
        if (productbarcodeValue.isEmpty()) {
            productbarcode.setError("It's Empty");
            productbarcode.requestFocus();
            return;
        }


        if(!TextUtils.isEmpty(productameValue)&&!TextUtils.isEmpty(productcategoryValue)&&!TextUtils.isEmpty(productpriceValue)){

            Products products = new Products(productameValue,productcategoryValue,productpriceValue,productbarcodeValue);
            databaseReference.child(resultemail).child("Products").child(productbarcodeValue).setValue(products);
            productname.setText("");
            productbarcode.setText("");
            productprice.setText("");
            productbarcode.setText("");
            Toast.makeText(this,productameValue+" Added",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();
        }
    }

    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AddProductActivity.this,ShopekeeperLoginActivity.class));
        Toast.makeText(this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

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
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}