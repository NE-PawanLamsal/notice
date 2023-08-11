package com.lamsal.pawan.yclnepal.studentapp.ui.faculty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lamsal.pawan.yclnepal.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView karyalayaDepartment,sachivalayaDepartment,aayogDepartment,centralDepartment;
    private LinearLayout karyalayaNoData,sachivalayaNoData,aayogNoData,centralNoData;
    private List<ShowTeacherData> list1,list2,list3,list4;
    private ShowTeacherAdapter adapter;
    private DatabaseReference reference,dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty, container, false);

        karyalayaDepartment= view.findViewById(R.id.studentKaryalayaDepartment);
        sachivalayaDepartment= view.findViewById(R.id.studentSachivalayaDepartment);
        aayogDepartment= view.findViewById(R.id.studentAayogDepartment);
        centralDepartment= view.findViewById(R.id.studentCentralDepartment);
        karyalayaNoData= view.findViewById(R.id.studentKaryalayaNoData);
        sachivalayaNoData= view.findViewById(R.id.studentSachivalayaNoData);
        aayogNoData= view.findViewById(R.id.studentAayogNoData);
        centralNoData= view.findViewById(R.id.studentCentralNoData);
        reference = FirebaseDatabase.getInstance().getReference().child("Committee");

        karyalayaDepartment();
        sachivalayaDepartment();
        aayogDepartment();
        centralDepartment();

        return view;
    }

    private void karyalayaDepartment() {
        dbRef = reference.child("Karyalaya");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    karyalayaNoData.setVisibility(View.VISIBLE);
                    karyalayaDepartment.setVisibility(View.GONE);
                }else {

                    karyalayaNoData.setVisibility(View.GONE);
                    karyalayaDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        ShowTeacherData data = snapshot.getValue(ShowTeacherData.class);
                        list1.add(data);
                    }
                    karyalayaDepartment.setHasFixedSize(true);
                    karyalayaDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new ShowTeacherAdapter(list1,getContext());
                    karyalayaDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
                        ShowTeacherData data = snapshot.getValue(ShowTeacherData.class);
                        list2.add(data);
                    }
                    sachivalayaDepartment.setHasFixedSize(true);
                    sachivalayaDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new ShowTeacherAdapter(list2,getContext());
                    sachivalayaDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
                        ShowTeacherData data = snapshot.getValue(ShowTeacherData.class);
                        list3.add(data);
                    }
                    aayogDepartment.setHasFixedSize(true);
                    aayogDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new ShowTeacherAdapter(list3,getContext()
                    );
                    aayogDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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
                        ShowTeacherData data = snapshot.getValue(ShowTeacherData.class);
                        list4.add(data);
                    }
                    centralDepartment.setHasFixedSize(true);
                    centralDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new ShowTeacherAdapter(list4,getContext());
                    centralDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}