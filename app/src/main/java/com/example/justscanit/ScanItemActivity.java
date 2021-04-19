package com.example.justscanit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ScanItemActivity extends AppCompatActivity {
    public static EditText resultsearcheview;
    ImageButton scantosearch;
    Button searchbtn;
    RecyclerView mrecyclerview;
    DatabaseReference mdatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item2);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser=users.getEmail();
        String resultemail = finaluser.replace(".","");
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("ShopKeeper").child(resultemail).child("Products");
        resultsearcheview = findViewById(R.id.searchfield);
        scantosearch = findViewById(R.id.imageButtonsearch);
        searchbtn = findViewById(R.id.searchbtnn);

        mrecyclerview = findViewById(R.id.recyclerViews);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(manager);
        mrecyclerview.setHasFixedSize(true);


        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));


        scantosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanCodeActivitysearch.class));
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtext = resultsearcheview.getText().toString();
                firebasesearch(searchtext);
            }
        });
    }

    public void firebasesearch(String searchtext){
        Query firebaseSearchQuery = mdatabaseReference.orderByChild("productbarcode").startAt(searchtext).endAt(searchtext+"\uf8ff");

        FirebaseRecyclerAdapter<Products, UsersViewHolder> ad = new FirebaseRecyclerAdapter<Products, UsersViewHolder>
                (  Products.class,
                        R.layout.list_layout,
                        UsersViewHolder.class,
                        firebaseSearchQuery )
        {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Products model,int position){

                viewHolder.setDetails(getApplicationContext(),model.getProductbarcode(),model.getProductcategory(),model.getProductname(),model.getProductprice());
            }
        };

        mrecyclerview.setAdapter(ad);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{
        static View mView;
        public UsersViewHolder(View itemView){
            super(itemView);
            mView =itemView;
        }

        public static void setDetails(Context ctx, String productbarcode, String productcategory, String productname, String productprice){
            TextView product_barcode = (TextView) mView.findViewById(R.id.product_barcode);
            TextView product_name = (TextView) mView.findViewById(R.id.product_name);
            TextView product_category = (TextView) mView.findViewById(R.id.product_category);
            TextView product_price = (TextView) mView.findViewById(R.id.product_price);

            product_barcode.setText(productbarcode);
            product_category.setText(productcategory);
            product_name.setText(productname);
            product_price.setText(productprice);
        }

    }
}