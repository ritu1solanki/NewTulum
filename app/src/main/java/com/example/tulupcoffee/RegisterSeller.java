package com.example.tulupcoffee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tulupcoffee.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterSeller extends AppCompatActivity {


   // ImageView profileIV;
    EditText firstName;
    EditText DeliveryFee;
    EditText ConfirmPassword;

    EditText address;
    EditText email;

    EditText password;
    EditText ShopName;
    EditText Phone;
    ImageView backsr;
    Button register;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

          backsr =(ImageView) findViewById(R.id.backsr);
       //  profileIV=(ImageView) findViewById(R.id.profileIV);

        firstName = findViewById(R.id.firstName);
        ShopName =findViewById(R.id.ShopName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        password = findViewById(R.id.password);
        DeliveryFee = findViewById(R.id.fee);
        Phone=findViewById(R.id.phone);
        ConfirmPassword=findViewById(R.id.confirmpassword);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }

            private String name,shopname,phoneno,deliveryfee,add,emailid,pass,confirmpass;

            private void inputData() {
                name=firstName.getText().toString().trim();
                shopname=ShopName.getText().toString().trim();
                phoneno=Phone.getText().toString().trim();
                deliveryfee=DeliveryFee.getText().toString().trim();
                add=address.getText().toString().trim();
                emailid=email.getText().toString().trim();
                pass=password.getText().toString().trim();
                confirmpass=ConfirmPassword.getText().toString().trim();


                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterSeller.this, "Enter Name", Toast.LENGTH_SHORT).show();

                }

                else if (TextUtils.isEmpty(shopname))
                {
                    Toast.makeText(RegisterSeller.this, "Enter ShopName", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(phoneno))
                {
                    Toast.makeText(RegisterSeller.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(deliveryfee))
                {
                    Toast.makeText(RegisterSeller.this, "Enter Delivery Fee", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(add))
                {
                    Toast.makeText(RegisterSeller.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(emailid))
                {
                    Toast.makeText(RegisterSeller.this, "Enter Emailid", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(pass))
                {
                    Toast.makeText(RegisterSeller.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(confirmpass))
                {
                    Toast.makeText(RegisterSeller.this, "Enter ConfirmPassword", Toast.LENGTH_SHORT).show();
                }

                createAccount();



            }

            private void createAccount() {
                progressDialog.setMessage("Creating account");
                progressDialog.show();

                firebaseAuth.createUserWithEmailAndPassword(emailid,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        saverfirebasedata();

                    }


                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterSeller.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });

            }
            private void saverfirebasedata() {
                progressDialog.setMessage("Saving Account Info...");
                String timestamp=""+ System.currentTimeMillis();

                HashMap<String, Object> userdataMap = new HashMap<>();
                userdataMap.put("uid",""+ firebaseAuth.getUid());
                userdataMap.put("email",""+ emailid);
                userdataMap.put("name",""+ name);
                userdataMap.put("email",""+ emailid);
                userdataMap.put("ShopName",""+ shopname);
                userdataMap.put("phone",""+ phoneno);
                userdataMap.put("DeliveryFee",""+ deliveryfee);
                userdataMap.put("email",""+ emailid);
                userdataMap.put("address",""+ add);
                userdataMap.put("email",""+ emailid);
                userdataMap.put("timestamp",""+ timestamp);
                userdataMap.put("AccountType",""+ "Seller");
                userdataMap.put("Online",""+ "true");
                userdataMap.put("ShopOpen",""+ "true");

                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
                ref.child(firebaseAuth.getUid()).setValue(userdataMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressDialog.dismiss();
                        startActivity(new Intent(RegisterSeller.this,MainSellerActivity.class));
                        finish();

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                startActivity(new Intent(RegisterSeller.this,MainSellerActivity.class));
                                finish();

                            }
                        });




            }
        });





        /* profileIV.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });*/


         backsr.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(RegisterSeller.this, LoginActivity.class);

                 startActivity(intent);
                 finish();
             }
         });






    }



}