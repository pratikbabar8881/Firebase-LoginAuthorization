package com.example.effcode.firebasedemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email,password;
    private Button login;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        email= findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.bt_login);

    login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        registerUser();

    }

    private void registerUser() {

        String email_check = email.getText().toString().trim();
        String password_check = password.getText().toString().trim();


        if(TextUtils.isEmpty(email_check))
        {
            Toast.makeText(MainActivity.this,"Enter Username",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password_check))
        {
            Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email_check,password_check)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Registration Error",Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();

                    }
                });

    }
}
