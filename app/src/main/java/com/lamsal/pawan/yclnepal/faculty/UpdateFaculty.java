package com.lamsal.pawan.yclnepal.faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lamsal.pawan.yclnepal.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView karyalayaDepartment,sachivalayaDepartment,aayogDepartment,centralDepartment;
    private LinearLayout karyalayaNoData,sachivalayaNoData,aayogNoData,centralNoData;
    private List<TeacherData> list1,list2,list3,list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference,dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        getSupportActionBar().hide();

        karyalayaDepartment=findViewById(R.id.karyalayaDepartment);
        sachivalayaDepartment=findViewById(R.id.sachivalayaDepartment);
        aayogDepartment=findViewById(R.id.aayogDepartment);
        centralDepartment=findViewById(R.id.centralDepartment);

        karyalayaNoData=findViewById(R.id.karyalayaNoData);
        sachivalayaNoData=findViewById(R.id.sachivalayaNoData);
        aayogNoData=findViewById(R.id.aayogNoData);
        centralNoData=findViewById(R.id.centralNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Committee");

        karyalayaDepartment();
        sachivalayaDepartment();
        aayogDepartment();
        centralDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(UpdateFaculty.this,AddTeacher.class));
                    }
                });

    }

    private void karyalayaDepartment() {
        dbRef = reference.child("Karyalaya");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    karyalayaNoData.setVisibility(View.VISIBLE);
                    karyalayaDepartment.setVisibility(View.GONE);
                }else {

                    karyalayaNoData.setVisibility(View.GONE);
                    karyalayaDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    karyalayaDepartment.setHasFixedSize(true);
                    karyalayaDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1,UpdateFaculty.this,"Karyalaya");
                    karyalayaDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void sachivalayaDepartment() {
        dbRef = reference.child("Sachivalaya");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    sachivalayaNoData.setVisibility(View.VISIBLE);
                    sachivalayaDepartment.setVisibility(View.GONE);
                }else {

                    sachivalayaNoData.setVisibility(View.GONE);
                    sachivalayaDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    sachivalayaDepartment.setHasFixedSize(true);
                    sachivalayaDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2,UpdateFaculty.this,"Sachivalaya");
                    sachivalayaDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void aayogDepartment() {
        dbRef = reference.child("Aayog & Bibhag");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    aayogNoData.setVisibility(View.VISIBLE);
                    aayogDepartment.setVisibility(View.GONE);
                }else {

                    aayogNoData.setVisibility(View.GONE);
                    aayogDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    aayogDepartment.setHasFixedSize(true);
                    aayogDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3,UpdateFaculty.this,"Aayog & Bibhag");
                    aayogDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void centralDepartment() {
        dbRef = reference.child("Central Committee");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    centralNoData.setVisibility(View.VISIBLE);
                    centralDepartment.setVisibility(View.GONE);
                }else {

                    centralNoData.setVisibility(View.GONE);
                    centralDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    centralDepartment.setHasFixedSize(true);
                    centralDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4,UpdateFaculty.this,"Central Committee");
                    centralDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}