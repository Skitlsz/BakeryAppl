package com.example.bakeryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);

    }

    public void homepage(View v) {
        String ver = uname.getText().toString();
        String ver2 = pass.getText().toString();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("User").child("Customers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Log.d("Firebase Info Valid", "");
                        DataSnapshot dataSnapshot = task.getResult();
                        String unameverif = String.valueOf(dataSnapshot.child("username").getValue());
                        String passverif = String.valueOf(dataSnapshot.child("password").getValue());
                        String emailverif = String.valueOf(dataSnapshot.child("email").getValue());
                        Log.d("Information: ", emailverif + " " + unameverif + " " + passverif);
                        User user = User.getInstance();
                        user.setUsername(unameverif);
                        user.setEmail(emailverif);
                        user.setPassword(ver2);

                        if (ver.equals(emailverif)||ver.equals(unameverif) && ver2.equals(passverif)) {
                            startActivity(new Intent(Login.this, Homepage.class));
                        }
                    }
                }
                else {
                    Toast.makeText(Login.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}