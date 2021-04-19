package com.example.justscanit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopekeeperRegsiterActivity extends AppCompatActivity {

EditText edtShopName,edtLicenseNumber,edtShopAddress,edtEmailID,edtPassword,edtCnfPassword,edtMobileNumber,edtShopkeeperName;
Button btnRegister;
TextView edtLogin;
CheckBox Food,Stationary,Electronics;
  FirebaseAuth mFireBaseAuth;
DatabaseReference ShopekeeperDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopekeeper_regsiter);
        btnRegister=findViewById(R.id.btnRegister);
        edtLogin=findViewById(R.id.login);
        edtShopName=findViewById(R.id.ShopName);
        edtShopAddress=findViewById(R.id.ShopAddress);
        edtShopkeeperName=findViewById(R.id.ShopKeeperName);
        edtLicenseNumber=findViewById(R.id.LicenseNumber);
        edtEmailID=findViewById(R.id.EmailAddress);
        edtPassword=findViewById(R.id.Password);
        edtCnfPassword=findViewById(R.id.ConfirmPassword);
        edtMobileNumber=findViewById(R.id.PhoneNumber);
        mFireBaseAuth = FirebaseAuth.getInstance();
        ShopekeeperDbRef = FirebaseDatabase.getInstance().getReference().child("ShopKeeper");




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ShopkeeperName=edtShopkeeperName.getText().toString();
                String ShopName=edtShopName.getText().toString();
                String LicenseNumber=edtLicenseNumber.getText().toString();
                String email=edtEmailID.getText().toString();
                String password=edtPassword.getText().toString();
                String cnfpassword=edtCnfPassword.getText().toString();
                String MobileNo=edtMobileNumber.getText().toString();
                String ShopAddress=edtShopAddress.getText().toString();

                if (email.isEmpty()) {
                    edtEmailID.setError("Please Enter EmailId..!!!");
                    edtEmailID.requestFocus();
                }
                if (ShopkeeperName.isEmpty()) {
                    edtShopkeeperName.setError("Please Enter Your Name!!!");
                    edtShopkeeperName.requestFocus();
                }
                if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                    edtEmailID.setError("Invalid Email Address");
                    edtEmailID.requestFocus();
                }
                if(ShopAddress.isEmpty())
                {
                    edtShopAddress.setError("Please Enter Your Shop Address...!!!!");
                    edtShopAddress.requestFocus();
                }
                if (ShopName.isEmpty()) {
                    edtShopName.setError("Please Enter Your Company Name!!!");
                    edtShopName.requestFocus();
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
                else if (LicenseNumber.length() != 15) {
                   edtLicenseNumber.setError("Enter Valid License Number..!!");
                    edtLicenseNumber.requestFocus();
                }
                else if (password.isEmpty()) {
                    edtPassword.setError("Please Enter Password..!!!");
                    edtPassword.requestFocus();
                } else if (!(password.isEmpty() && email.isEmpty())) {
                    mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(ShopekeeperRegsiterActivity.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(task12 -> {
                                if (task12.isSuccessful()){
                                    Log.d("EMAIL_VERIFICATION","EMAIL SENT");
                                }
                            });

                            Toast.makeText(ShopekeeperRegsiterActivity.this, "Registration  Successful!!!! ", Toast.LENGTH_SHORT).show();
                            Intent intToHome = new Intent(ShopekeeperRegsiterActivity.this, ShopekeeperLoginActivity.class);
                            startActivity(intToHome);
                            insertShopekeeperData();
                        }
                        else{
                            Toast.makeText(ShopekeeperRegsiterActivity.this, "Registration  Unsuccessful!!!! ", Toast.LENGTH_SHORT).show();
                        }

                    });
                }

            }
        });
        edtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopekeeperRegsiterActivity.this,ShopekeeperLoginActivity.class));
            }
        });

    }
    private void insertShopekeeperData() {
        String ShopkeeperName=edtShopkeeperName.getText().toString();
        String ShopName=edtShopName.getText().toString();
        String  LicenseNumber=edtLicenseNumber.getText().toString();
        String email=edtEmailID.getText().toString();
        String password=edtPassword.getText().toString();
        String MobileNo=edtMobileNumber.getText().toString();
        String ShopAddress=edtShopAddress.getText().toString();
        /*  String pd1="Food";
        String pd2="Stationary";
        String pd3="Electronics";*/
       ShopKeeper shopKeeper=new ShopKeeper(ShopkeeperName,ShopName,LicenseNumber,ShopAddress,email,password,MobileNo);
       ShopekeeperDbRef.push().setValue(shopKeeper);
       Toast.makeText(ShopekeeperRegsiterActivity.this,"Your Date is stored!!!",Toast.LENGTH_SHORT).show();
    }
}