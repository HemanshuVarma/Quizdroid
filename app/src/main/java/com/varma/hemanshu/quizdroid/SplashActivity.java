package com.varma.hemanshu.quizdroid;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Splash Activity
 * Runtime 1500 Milli Sec
 * Redirecting to UserDetails Activity
 */
public class SplashActivity extends AppCompatActivity {

    //Setting Splash Delay Length
    private final static int SPLASH_DELAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, UserDetailsActivity.class));
                finish();
            }
        }, SPLASH_DELAY_LENGTH);
    }
}
