package com.example.gavitexpress.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gavitexpress.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openLoginActivity(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }

    public void openRegisterActivity(View view)
    {
        startActivity(new Intent(HomeActivity.this,RegisterActivity.class));
    }

    public void openMainActivity(View view) {

        startActivity(new Intent(HomeActivity.this,MainActivity.class));
    }
}
