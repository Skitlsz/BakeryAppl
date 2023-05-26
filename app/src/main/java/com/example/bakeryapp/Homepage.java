package com.example.bakeryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Homepage extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        User user = User.getInstance();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("User").child("Customers").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String u = String.valueOf(dataSnapshot.child("username").getValue());
                        String e = String.valueOf(dataSnapshot.child("email").getValue());
                        String p = String.valueOf(dataSnapshot.child("password").getValue());


                    }
                }
            }
        });
        Log.d("user", username);
        welcome = findViewById(R.id.welcome);
        welcome.setText("Welcome " + username);
    }
    public void msg(View v){
        startActivity(new Intent(Homepage.this, Messaging.class));
    }
}