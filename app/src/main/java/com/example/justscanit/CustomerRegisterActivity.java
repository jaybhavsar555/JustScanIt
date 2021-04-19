package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerRegisterActivity extends AppCompatActivity {
        EditText Name,Emailid,Password,CnfPassword,PhoneNumber;
        Button btnRegister;
        TextView TextViewLogin;
        FirebaseAuth mFireBaseAuth;
        DatabaseReference CustomerDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        mFireBaseAuth = FirebaseAuth.getInstance();
        Name = findViewById(R.id.Name);
        Emailid = findViewById(R.id.EmailID);
        Password = findViewById(R.id.Password);
        CnfPassword = findViewById(R.id.ConfirmPassword);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        TextViewLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.btnRegister);
        CustomerDbRef = FirebaseDatabase.getInstance().getReference().child("Customer");
        btnRegister.setOnClickListener(v -> {

            String email = Emailid.getText().toString();
            String pwd = Password.getText().toString();
            String cnfpwd = CnfPassword.getText().toString();
            String name = Name.getText().toString();
            String PhoneNo=PhoneNumber.getText().toString();
            if (email.isEmpty()) {
                Emailid.setError("Please Enter EmailId..!!!");
                Emailid.requestFocus();
            }
            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                Emailid.setError("Invalid Email Address");
                Emailid.requestFocus();
            }
            if (name.isEmpty()) {
                Name.setError("Please Enter Your Name!!!...");
                Name.requestFocus();
            } else if (!(pwd.equals(cnfpwd))) {
                CnfPassword.setError("Please Enter Proper Password as entered before....");
                CnfPassword.requestFocus();
            }  else if (pwd.length()<6) {
                Password.setError("Password length must be greater then 6 ... !!!");
                Password.requestFocus();
            }
          else if (PhoneNo.length()!=10) {
            PhoneNumber.setError("Enter Valid Phone Number...");
            PhoneNumber.requestFocus();
        }
            else if (pwd.isEmpty()) {
                Password.setError("Please Enter Password..!!!");
                Password.requestFocus();
            } else if (!(pwd.isEmpty() && email.isEmpty())) {
                mFireBaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(CustomerRegisterActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(task12 -> {
                            if (task12.isSuccessful()){
                                Log.d("EMAIL_VERIFICATION","EMAIL SENT");
                            }
                        });



                        Toast.makeText(CustomerRegisterActivity.this, "Registration  Successful!!!! ", Toast.LENGTH_SHORT).show();
                        Intent intToHome = new Intent(CustomerRegisterActivity.this, CustomerLoginActivity.class);
                        startActivity(intToHome);
                        insertCustomerData();
                    }
                    else{
                        Toast.makeText(CustomerRegisterActivity.this, "Registration  Unsuccessful!!!! ", Toast.LENGTH_SHORT).show();
                    }

                });
            }




        });
        TextViewLogin.setOnClickListener(v -> startActivity(new Intent(CustomerRegisterActivity.this, CustomerLoginActivity.class)));
    }
    private void insertCustomerData() {
        String name=Name.getText().toString();
        String Email=Emailid.getText().toString();
        String MobileNo=PhoneNumber.getText().toString();
        String password=Password.getText().toString();
        Customer customer=new Customer(name,Email,MobileNo,password);
        CustomerDbRef.push().setValue(customer);
        Toast.makeText(CustomerRegisterActivity.this,"Your Date is stored!!!",Toast.LENGTH_SHORT).show();

    }
}