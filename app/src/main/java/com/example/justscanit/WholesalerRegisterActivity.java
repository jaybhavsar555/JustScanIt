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

public class WholesalerRegisterActivity extends AppCompatActivity {
TextView txtLogin;
EditText edtWholesalerName,edtCompanyName,edtDistributionID,edtEmailID,edtPassword,edtCnfPassword,edtMobileNumber;
Button btnRegister;
FirebaseAuth mFireBaseAuth;
DatabaseReference WholesalerDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_register);
        mFireBaseAuth = FirebaseAuth.getInstance();
        txtLogin=findViewById(R.id.login);
        edtWholesalerName=findViewById(R.id.WholesalerName);
        edtCompanyName=findViewById(R.id.CompanyName);
        edtDistributionID=findViewById(R.id.DistributionID);
        edtEmailID=findViewById(R.id.EmailAddress);
        edtPassword=findViewById(R.id.Password);
        edtCnfPassword=findViewById(R.id.ConfirmPassword);
        edtMobileNumber=findViewById(R.id.MobileNumber);
        btnRegister=findViewById(R.id.btnRegister);
        WholesalerDbRef = FirebaseDatabase.getInstance().getReference().child("Wholesaler");
        btnRegister.setOnClickListener(v -> {
            String WholesalerName=edtWholesalerName.getText().toString();
            String CompanyName=edtCompanyName.getText().toString();
             String  DistributionID=edtDistributionID.getText().toString();
             String email=edtEmailID.getText().toString();
            String password=edtPassword.getText().toString();
            String cnfpassword=edtCnfPassword.getText().toString();

           String MobileNo=edtMobileNumber.getText().toString();
           if (email.isEmpty()) {
                edtEmailID.setError("Please Enter EmailId..!!!");
                edtEmailID.requestFocus();
            }
            if (WholesalerName.isEmpty()) {
                edtWholesalerName.setError("Please Enter Your Name!!!");
                edtWholesalerName.requestFocus();
            }

            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                edtEmailID.setError("Invalid Email Address");
                edtEmailID.requestFocus();
            }
            if (CompanyName.isEmpty()) {
                edtCompanyName.setError("Please Enter Your Company Name!!!");
                edtCompanyName.requestFocus();
            }else if (!(password.equals(cnfpassword))) {
                edtCnfPassword.setError("Please Enter Proper Password as entered before....");
                edtCnfPassword.requestFocus();
            }  else if (password.length()<6) {
                edtPassword.setError("Password length must be greater then 6 ... !!!");
                edtPassword.requestFocus();
            }
            else if (MobileNo.length()!=10) {
                edtMobileNumber.setError("Enter Valid Phone Number...");
                edtMobileNumber.requestFocus();
            }
            else if (DistributionID.length()<9 ) {
                edtDistributionID.setError("Enter Valid Distribution ID...!!");
                edtDistributionID.requestFocus();
            }
            else if (password.isEmpty()) {
                edtPassword.setError("Please Enter Password..!!!");
                edtPassword.requestFocus();
            } else if (!(password.isEmpty() && email.isEmpty())) {
                mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(WholesalerRegisterActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(task12 -> {
                            if (task12.isSuccessful()){
                                Log.d("EMAIL_VERIFICATION","EMAIL SENT");
                            }
                        });

                        Toast.makeText(WholesalerRegisterActivity.this, "Registration  Successful!!!! ", Toast.LENGTH_SHORT).show();
                        Intent intToHome = new Intent(WholesalerRegisterActivity.this, WholesalerLoginActivity.class);
                        startActivity(intToHome);
                        insertWholesalerData();
                    }
                    else{
                        Toast.makeText(WholesalerRegisterActivity.this, "Registration  Unsuccessful!!!! ", Toast.LENGTH_SHORT).show();
                    }

                });
            }




        });
        txtLogin.setOnClickListener(v -> startActivity(new Intent(WholesalerRegisterActivity.this,WholesalerLoginActivity.class)));
    }

    private void insertWholesalerData() {
        String WholesalerName=edtWholesalerName.getText().toString();
        String CompanyName=edtCompanyName.getText().toString();
        String DistributionID=edtDistributionID.getText().toString();
        String emailID=edtEmailID.getText().toString();
        String password=edtPassword.getText().toString();
        String MobileNo=edtMobileNumber.getText().toString();
        Wholesaler wholesaler=new Wholesaler(WholesalerName,CompanyName,DistributionID,emailID,password,MobileNo);
        WholesalerDbRef.push().setValue(wholesaler);
        Toast.makeText(WholesalerRegisterActivity.this,"Your Date is stored!!!",Toast.LENGTH_SHORT).show();



    }
}

