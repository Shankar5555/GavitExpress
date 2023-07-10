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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText reg_name, reg_email,reg_pass,reg_phno;
    ProgressBar reg_progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_name = (EditText) findViewById(R.id.reg_name);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);
        reg_phno = (EditText) findViewById(R.id.reg_phno);

        reg_progressBar = (ProgressBar) findViewById(R.id.reg_progressBar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

//-----------------------------------------------------------------------------------------
    public void openLoginActivityy(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
//-----------------------------------------------------------------------------------------
    public void register(View view)
    {
        String name1 = reg_name.getText().toString();
        String email1 = reg_email.getText().toString();
        String pass1 = reg_pass.getText().toString();
        String phno1 = reg_phno.getText().toString();


        if (name1.trim().equals(""))
        {
            reg_name.setError("Name cannot ne empty");
            reg_name.setFocusable(true);

        }
        else if(email1.trim().equals(""))
        {
            reg_email.setError("Email cannot ne empty");
            reg_email.setFocusable(true);
        }
        else if(pass1.trim().equals(""))
        {
            reg_pass.setError("Password cannot ne empty");
            reg_pass.setFocusable(true);
        }
        else if(phno1.trim().equals(""))
        {
            reg_phno.setError("Phone no cannot ne empty");
            reg_phno.setFocusable(true);
        }else
        {
            //reg_progressBar.setVisibility(View.VISIBLE);
            UserDetails userDetails = new UserDetails();
            userDetails.setName(name1);
            userDetails.setEmail(email1);
            userDetails.setPhoneno(phno1);


            mAuth.createUserWithEmailAndPassword(email1,pass1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                //Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("register");
                                reference.child(mAuth.getCurrentUser().getUid()).setValue(userDetails)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                {
                                                    reg_progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(RegisterActivity.this, "User Registred Successfully", Toast.LENGTH_SHORT).show();

                                                }
                                                else
                                                {
                                                    reg_progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(RegisterActivity.this, "User Registred Failed ", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        });
                            }
                            else
                            {
                                reg_progressBar.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                            }

                        }


                    });
            ClearText();

        }

    }

    public void ClearText()
    {
        reg_name.getText().clear();
        reg_email.getText().clear();
        reg_phno.getText().clear();
        reg_pass.getText().clear();
    }

}

