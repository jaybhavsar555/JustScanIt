package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    TextView firebasenameview;

    CardView addItems, deleteItems, scanItems, viewInventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        firebasenameview = findViewById(R.id.firebasename);

        // this is for username to appear after login

        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser= Objects.requireNonNull(users).getEmail();
        String result = Objects.requireNonNull(finaluser).substring(0, finaluser.indexOf("@"));
        String resultemail = result.replace(".","");
        firebasenameview.setText("Welcome"+"\n"+resultemail);
        addItems = findViewById(R.id.addItems);
        deleteItems = findViewById(R.id.deleteItems);
        scanItems = findViewById(R.id.scanItems);
        viewInventory =  findViewById(R.id.viewInventory);

        addItems.setOnClickListener(this);
        deleteItems.setOnClickListener(this);
        scanItems.setOnClickListener(this);
        viewInventory.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.addItems : i = new Intent(this,AddProductActivity.class); startActivity(i); break;
            case R.id.deleteItems : i = new Intent(this,deleteitemActivity.class);startActivity(i); break;
            case R.id.scanItems : i = new Intent(this,ScanItemActivity.class);startActivity(i); break;
            case R.id.viewInventory : i = new Intent(this,viewInventoryActivity.class);startActivity(i); break;
            default: break;
        }
    }



    // logout below
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,ShopekeeperLoginActivity.class));
        Toast.makeText(DashBoardActivity.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logoutMenu) {
            Logout();
        }
        if(item.getItemId()==R.id.feedback)
        {
            feedback();
        }
        return super.onOptionsItemSelected(item);
    }

    private void feedback() {
        startActivity(new Intent(this,SendFeedbackActivity.class));
        Toast.makeText(DashBoardActivity.this,"Opening Feedback Page...", Toast.LENGTH_SHORT).show();
    }

}