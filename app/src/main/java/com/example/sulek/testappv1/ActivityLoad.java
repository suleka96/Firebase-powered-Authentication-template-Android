package com.example.sulek.testappv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sulek on 11/09/2017.
 */

public class ActivityLoad extends Activity {

    private FirebaseAuth user;
    int timeout = 1500;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        user = FirebaseAuth.getInstance();

    }

    public void onStart(View view) {
        super.onStart();
        Timer timer = new Timer();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = user.getCurrentUser();


        if(currentUser != null){

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                    Toast.makeText(ActivityLoad.this, "Going Home!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ActivityLoad.this, ActivityHome.class);
                    startActivity(i);
                }
            }, timeout);

        }
        if(currentUser == null){

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                    Toast.makeText(ActivityLoad.this, "Sign in!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ActivityLoad.this, ActivitySignin.class);
                    startActivity(i);
                }
            }, timeout);

        }
    }



}
