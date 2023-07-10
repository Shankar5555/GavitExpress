package com.example.gavitexpress.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gavitexpress.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText log_email, log_pass;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_email = (EditText) findViewById(R.id.log_email);
        log_pass = (EditText) findViewById(R.id.log_pass);

        mAuth=FirebaseAuth.getInstance();

    }

    public void openRegisterActivityy(View view)
    {

        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void login(View view)
    {
        String email1 = log_email.getText().toString();
        String pass1 = log_pass.getText().toString();

        mAuth.signInWithEmailAndPassword(email1,pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
