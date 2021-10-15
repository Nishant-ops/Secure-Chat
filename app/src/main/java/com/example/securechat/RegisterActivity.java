package com.example.securechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
private EditText mUsername;
private EditText mEmail;
private EditText mPassword;
private TextView mRegister;
private FirebaseAuth mfirebaseAuth;
private DatabaseReference mdatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername = (EditText) findViewById(R.id.Username_Register);
        mEmail = (EditText) findViewById(R.id.Email_Register);
        mPassword = (EditText) findViewById(R.id.Password_Register);
        mRegister = (TextView) findViewById(R.id.Register_Register);
        mfirebaseAuth = FirebaseAuth.getInstance();
    mRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String txt_username=mUsername.getText().toString();
            String txt_email=mEmail.getText().toString().trim();
            String txt_password=mPassword.getText().toString();
           /* InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);*/

            if(TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password))
            {
                Toast.makeText(RegisterActivity.this,"All field should be field",Toast.LENGTH_SHORT).show();
            }
            else if(txt_password.length()<6)
            {
                Toast.makeText(RegisterActivity.this,"Password should be atleast 8 characters",Toast.LENGTH_SHORT).show();
            }
            else{
                registe(txt_username,txt_email,txt_password);
            }
        }
    });

            }



    private void registe ( String username, String email,String password) {
        mfirebaseAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.i(LOCATION_SERVICE,"worked");
                            FirebaseUser user=mfirebaseAuth.getCurrentUser();
                            String uid=user.getUid();
                            mdatabaseReference= FirebaseDatabase.getInstance().getReference("users").child(uid);
                            Log.i(LOCATION_SERVICE,"issue"+uid);
                            HashMap<String,String> map=new HashMap<>();
                            map.put("id",uid);
                            map.put("Username",username);
                            mdatabaseReference.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this,"Welcome Please Login to Start",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });

                        }
                        else{
                            Log.w(LOCATION_SERVICE, "createUserWithEmail:failure", task.getException());

                        }
                    }
                });
    }

}