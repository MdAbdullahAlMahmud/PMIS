package com.mkrlabs.pmis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.mkrlabs.pmis.ui.HomeActivity;

public class SplashActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;
                if (mAuth.getCurrentUser()!=null){
                    intent = new Intent(SplashActivity.this, HomeActivity.class);

                }else {
                    intent = new Intent(SplashActivity.this, AuthenticationActivity.class);

                }

                startActivity(intent);
                finish();
            }
        },3000);
    }
}