package com.example.securechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
private EditText mEmail;
private EditText mPassword;
private TextView mLogin;
private DatabaseReference mdatabaseReference;
private FirebaseAuth mfirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail=(EditText) findViewById(R.id.Username_Login);
        mPassword=(EditText) findViewById(R.id.Password_Login);
        mLogin=(TextView) findViewById(R.id.Login_Login);
        mfirebaseAuth =FirebaseAuth.getInstance();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_Email=mEmail.getText().toString().trim();
                String txt_password=mPassword.getText().toString();



                if(TextUtils.isEmpty(txt_Email)||TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(LoginActivity.this,"all fields should be field",Toast.LENGTH_SHORT).show();
                }
                register(txt_Email,txt_password);
            }
        });

    }

    private  void register(String email,String password)
    {
        mfirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=mfirebaseAuth.getCurrentUser();
                    if (user != null) {
                        // Name, email address, and profile photo Url
                        mdatabaseReference= FirebaseDatabase.getInstance().getReference("users").child(mfirebaseAuth.getUid());
                        String name=user.getDisplayName();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);
                       }
                }
                else{
                    Log.i(LOCATION_SERVICE,"User does not exist",task.getException());
                }
            }
        });
    }
}