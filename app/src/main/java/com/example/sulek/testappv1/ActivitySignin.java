package com.example.sulek.testappv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by sulek on 11/09/2017.
 */

public class ActivitySignin extends Activity {

    private FirebaseAuth user;
    private EditText mpassword;
    private EditText musername;
    private Button signup;
    private Button sigin;
    private static final String TAG = "EmailPassword";


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        musername = (EditText) findViewById(R.id.txt1);
        mpassword = (EditText) findViewById(R.id.txt2);
        signup = (Button)findViewById(R.id.button2);
        sigin = (Button)findViewById(R.id.button1);

        user = FirebaseAuth.getInstance();

        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            };
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivitySignin.this, "Going Home!", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ActivitySignin.this, MainActivity.class);
                startActivity(i);
            };
        });


    }

    protected void signIn(){
        final String username = musername.getText().toString();
        final String password = mpassword.getText().toString();
        try{
            user.signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Log.d(TAG,"signInWithEmail:success");
                                FirebaseUser person =user.getCurrentUser();
                                Toast.makeText(ActivitySignin.this, "Authentication Successful.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(ActivitySignin.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();


                            }
                        }
                    });

        }
        catch(Exception e){}
    }
}
