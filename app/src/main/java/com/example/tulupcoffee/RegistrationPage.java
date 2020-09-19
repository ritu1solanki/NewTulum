package com.example.tulupcoffee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tulupcoffee.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText address;
    EditText email;
    Button register;
    EditText password;
    TextView r;
    ImageView backr;
    


    NewUser newUser;
    FirebaseAuth mAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReferenceFromUrl("https://tulum-coffee.firebaseio.com/");
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);


        ImageView back = (ImageView) findViewById(R.id.backr);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationPage.this, LoginActivity.class);

                startActivity(intent);
                finish();


            }
        });






        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        password = findViewById(R.id.password);
        r =findViewById(R.id.r);
        //confirmpassword = findViewById(R.id.confirmpassword);
        newUser = new NewUser();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() !=null){
                    startActivity(new Intent(RegistrationPage.this,Menu.class));
                    finish();
                }
            }
        };

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegistrationPage.this,RegisterSeller.class));
                finish();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                if(email.getText().toString().isEmpty() | password.getText().toString().isEmpty())
                    Toast.makeText(RegistrationPage.this, "Please Enter the details", Toast.LENGTH_SHORT).show();
                else {
                    newUser.setName(firstName.getText().toString());
                    newUser.setLastname(lastName.getText().toString());
                    newUser.setAddress(address.getText().toString());
                    newUser.setEmailid(email.getText().toString());
                    newUser.setPassword(password.getText().toString());
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful())
                               Toast.makeText(RegistrationPage.this, "Error", Toast.LENGTH_SHORT).show();
                           else{
                               FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                               if(user != null){
                                   final DatabaseReference userRef = ref.child("Ritu").child("Users").child(user.getUid());
                                   userRef.setValue(newUser);

                               }
                           }
                        }
                    });
                }
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }

        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
}




