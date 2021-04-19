package com.example.justscanit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class viewInventoryActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    RecyclerView mrecyclerview;
    DatabaseReference mdatabaseReference;
    private TextView totalnoofitem, totalnoofsum;
    private int counttotalnoofitem =0;
    public List<Products> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);
        totalnoofitem = findViewById(R.id.totalnoitem);
        totalnoofsum = findViewById(R.id.totalsum);
        firebaseAuth = FirebaseAuth.getInstance();
         FirebaseUser users = firebaseAuth.getCurrentUser();
        assert users != null;
        String finaluser = users.getEmail();
        assert finaluser != null;
        String resultemail = finaluser.replace(".", "");
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("ShopKeeper").child(resultemail).child("Products");
        mrecyclerview = findViewById(R.id.recyclerViews);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(manager);
        mrecyclerview.setHasFixedSize(true);
     // mrecyclerview.setLayoutManager(new LinearLayoutManager(this));


        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    counttotalnoofitem = (int) dataSnapshot.getChildrenCount();
                    totalnoofitem.setText(Integer.toString(counttotalnoofitem));
                } else {
                    totalnoofitem.setText("0");
                }
                int sum=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("productprice");
                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;
                    totalnoofsum.setText(String.valueOf(sum));

                }
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Log.d("SNAP",ds.getValue().toString());
                    Products model = ds.getValue(Products.class);
                    list.add(model);
                }

                ViewInventoryAdapter adapter = new ViewInventoryAdapter(list);
                mrecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
    }

}