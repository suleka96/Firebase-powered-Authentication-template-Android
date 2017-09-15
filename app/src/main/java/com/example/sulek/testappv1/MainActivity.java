package com.example.sulek.testappv1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText mpassword;
    private EditText musername;
    private Button mbutton;
    private String id;
    private FirebaseAuth userauth;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musername = (EditText) findViewById(R.id.txt1);
        mpassword = (EditText) findViewById(R.id.txt2);
        mbutton = (Button)findViewById(R.id.button2);

        userauth = FirebaseAuth.getInstance();

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            };
        });



    }


    public void signUp(){

        final String username = musername.getText().toString();
        final String password = mpassword.getText().toString();
        try {

            userauth.createUserWithEmailAndPassword(username, password).
                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = userauth.getCurrentUser();
                                UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(username)
                                        .build();
                                id = user.getUid().toString();

                                user.updateProfile(profileUpdate)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            public static final String TAG = "LogData";

                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile updated");
                                                }
                                            }
                                        });
                                mRootRef.child(id).child("Username").setValue(username);
                                mRootRef.child(id).child("Password").setValue(password);

                                Toast.makeText(MainActivity.this, "Successful!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(MainActivity.this, ActivityHome.class);
                                startActivity(i);
                            }
                            else {

                                Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT);

                            }
                        }

                    });
        }
        catch (Exception e){}
    }
}
