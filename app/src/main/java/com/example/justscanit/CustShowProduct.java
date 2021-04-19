package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustShowProduct extends AppCompatActivity {
    public DatabaseReference mdatabaseReference = FirebaseDatabase.getInstance().getReference().child("ShopKeeper").child("Products");
    FirebaseAuth firebaseAuth;
    public ArrayList<Products> list = new ArrayList<>();
    public RecyclerView recyclerView;
    public SearchView searchView;
    public AdapterClass adapterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_show_product);
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
        firebaseAuth = FirebaseAuth.getInstance();
        adapterClass = new AdapterClass(CustShowProduct.this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterClass);


    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("ShopKeeper");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        for (DataSnapshot snap : ds.getChildren()) {
                            Products products = snap.getValue(Products.class);
                            list.add(products);
                        }
                    }
                }
                recyclerView.setAdapter(adapterClass);
                adapterClass.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (searchView != null) {

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return false;
                }
            });

        }
    }
    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,ShopekeeperLoginActivity.class));
        Toast.makeText(CustShowProduct.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

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
        startActivity(new Intent(CustShowProduct.this,SendFeedbackActivity.class));
        Toast.makeText(CustShowProduct.this,"Opening Feedback Page...", Toast.LENGTH_SHORT).show();
    }


    private void search(String str) {
        ArrayList<Products> myList= new ArrayList<>();
        for(Products products:list)
        {// Log.d("SNAP1",products.getItemname());
            if(products.getProductname().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(products);

            }
        }
        AdapterClass adapterClass=new AdapterClass(  CustShowProduct.this,myList);
        recyclerView.setAdapter(adapterClass);
        adapterClass.notifyDataSetChanged();
    }

}

