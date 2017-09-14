package com.example.sulek.testappv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sulek on 11/09/2017.
 */

public class ActivityLoad extends Activity {

    private FirebaseAuth user;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        user = FirebaseAuth.getInstance();

    }

    public void onStart(View view) {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = user.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(ActivityLoad.this, "Going Home!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(ActivityLoad.this, ActivityHome.class);
            startActivity(i);
        }
        if(currentUser == null){
            Toast.makeText(ActivityLoad.this, "Sign in!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(ActivityLoad.this, ActivitySignin.class);
            startActivity(i);
        }
    }



}
