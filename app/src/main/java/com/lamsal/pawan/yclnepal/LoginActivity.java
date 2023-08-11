package com.lamsal.pawan.yclnepal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lamsal.pawan.yclnepal.studentapp.DashboardActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private Button login;
    Switch active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        login=findViewById(R.id.login);
        active=findViewById(R.id.active);
        getSupportActionBar().hide();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();


                        if (dataSnapshot.child(input1).exists()){
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)){
                                if (active.isChecked()){
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")){
                                        preferences.setDataLogin(LoginActivity.this,true);
                                        preferences.setDataAs(LoginActivity.this,"admin");
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(LoginActivity.this,true);
                                        preferences.setDataAs(LoginActivity.this,"user");
                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                    }
                                }else{
                                  if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")){
                                        preferences.setDataLogin(LoginActivity.this,false);
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(LoginActivity.this,false);
                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                    }
                                }
                            }else {
                                Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(LoginActivity.this, "No such user recognised", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)){
            if (preferences.getDataAs(this).equals("admin")){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }else if (preferences.getDataAs(this).equals("user")){
                startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                finish();
            }
        }
    }
}