package com.example.tulupcoffee.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tulupcoffee.Menu;
import com.example.tulupcoffee.R;
import com.example.tulupcoffee.RegistrationPage;
import com.example.tulupcoffee.SellerLogin;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    //private LoginViewModel loginViewModel;
    public CallbackManager mCallbackManager;
    public FirebaseAuth.AuthStateListener authStateListener;
    public AccessTokenTracker accessTokenTracker;
    public static final String TAG="FACELOG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        mCallbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);



        mAuth = FirebaseAuth.getInstance();
        ActionBar actionBar = getSupportActionBar();
     actionBar.hide();
//        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
//                .get(LoginViewModel.class);


        // Initialize Facebook Login button

        //LoginButton loginButton = mBinding.buttonFacebookLogin;
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
        loginButton.setReadPermissions("email","public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d(TAG,"onSuccess"+loginResult);
                Toast.makeText(getApplicationContext(),"Logged In..",Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
                Intent intent = new Intent(LoginActivity.this, Menu.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onError", error.getCause().toString());
            }
        });

        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!= null)
                {
                    //     updateUI();
                }
                else {
                    //   updateUI();
                }
            }
        };

       /*accessTokenTracker= new AccessTokenTracker() {
           @Override
           protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
               if(currentAccessToken==null)
               {
                   mAuth.signOut();
               }
           }
       };


*/



        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButt = findViewById(R.id.login);
        final TextView vendortv= findViewById(R.id.vendortv);
        //final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button SignUp = findViewById(R.id.SignUp);



        vendortv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, SellerLogin.class));
                finish();

            }
        });


        //CODE FOR LOGIN
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    String input = "Authentication Failed";
                                    input += "\n";
                                    input += "Please Enter Registered Mail ID and Password";
                                    input += "\n";
                                    input += "Or Create new account";
                                    Toast.makeText(LoginActivity.this, input, Toast.LENGTH_LONG).show();



                                } else {
                                    startActivity(new Intent(LoginActivity.this, Menu.class));
                                    finish();





/*
                                    HashMap<String, Object> userdataMap = new HashMap<>();
                                    userdataMap.put("online","true");
                                    final FirebaseDatabase database=FirebaseDatabase.getInstance();

                                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");



                                    ref.child(firebaseAuth.getUid()).updateChildren(userdataMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            checkUserType();



                                        }





                                        private void checkUserType() {

                                            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
                                            ref.orderByChild("uid").equalTo(firebaseAuth.getUid())
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            for(DataSnapshot ds:snapshot.getChildren())
                                                            {
                                                                String accounttype=""+ds.child("AccountType").getValue().toString();
                                                                Toast.makeText(LoginActivity.this,accounttype,Toast.LENGTH_SHORT).show();
                                                                if(accounttype.equals("Seller"))
                                                                {
                                                                    progressDialog.dismiss();
                                                                    startActivity(new Intent(LoginActivity.this, MainSellerActivity.class));
                                                                    finish();
                                                                }
                                                                else{
                                                                    progressDialog.dismiss();
                                                                    startActivity(new Intent(LoginActivity.this, Menu.class));
                                                                    finish();
                                                                }
                                                            }

                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });

                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {



                                                }
                                            });*/




                                }
                            }
                        });
            }
        });

        //REGISTER
        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CODE FOR REGISTER
                startActivity(new Intent(LoginActivity.this, RegistrationPage.class));
                finish();
                //makemeonline();


            }



        });




    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(authStateListener);
        FirebaseUser currentUser= mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            //updateUI();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null)
        {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

   /* private void updateUI() {
        Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, Menu.class);
        startActivity(intent);
        finish();


    }*/



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }




    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI();
                        }

                        // ...
                    }
                });


    }




}