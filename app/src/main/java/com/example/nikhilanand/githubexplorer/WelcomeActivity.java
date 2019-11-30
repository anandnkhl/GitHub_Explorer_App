package com.example.nikhilanand.githubexplorer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );
        moveToMainActivity();
    }

    public void moveToMainActivity()
    {
        new Handler().postDelayed( new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(WelcomeActivity.this,MainActivity.class);
                WelcomeActivity.this.startActivity(mainIntent);
                WelcomeActivity.this.finish();
            }
        }, 1000);

    }
}
