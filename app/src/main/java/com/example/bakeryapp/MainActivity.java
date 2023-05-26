package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editText1,editText2,editText3,editText4;
    FirebaseDatabase db;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("On Create Test", "working");
        editText1 = findViewById(R.id.fname);
        editText2 = findViewById(R.id.uname);
        editText3 = findViewById(R.id.pass);
        editText4 = findViewById(R.id.cpass);


    }


    public void login(View v) {
        Intent intent = new Intent (MainActivity.this, Login.class);
        startActivity(intent);
    }

    public void register(View v) {
        String name = editText1.getText().toString();
        String email = editText2.getText().toString();
        String password = editText3.getText().toString();
        String cpassword = editText4.getText().toString();
        if(!name.isEmpty() && !email.isEmpty() && email.contains("@")&& !password.isEmpty() && password.length() >= 8 && cpassword.equals(password)){
            User user = new User (name, email, password);
            Log.d("Information", user + "");
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("User");
            reference.child("Customers").setValue(user);
            Toast.makeText(this, "You have successfully registered!", Toast.LENGTH_SHORT).show();
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
        } else if(password.length() < 8){
            Toast.makeText(this, "Password must contain 8 characters.", Toast.LENGTH_SHORT).show();
        } else if(!cpassword.equals(password)){
            Toast.makeText(this, "Incorrect password.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Invalid, please fill in the blanks.", Toast.LENGTH_SHORT).show();
        }
    }

}