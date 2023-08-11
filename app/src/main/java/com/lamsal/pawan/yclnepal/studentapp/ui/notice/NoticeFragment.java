package com.lamsal.pawan.yclnepal.studentapp.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lamsal.pawan.yclnepal.R;
import com.lamsal.pawan.yclnepal.notice.DeleteNoticeActivity;
import com.lamsal.pawan.yclnepal.notice.NoticeAdapter;
import com.lamsal.pawan.yclnepal.notice.NoticeData;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    private RecyclerView showNoticeRecycler;
    private ProgressBar studentProgressBar;
    private ArrayList<ShowNoticeData> list;
    private ShowNoticeAdapter adapter;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notice, container, false);

        showNoticeRecycler= view.findViewById(R.id.showNoticeRecycler);
        studentProgressBar= view.findViewById(R.id.studentProgressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        showNoticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        showNoticeRecycler.setHasFixedSize(true);
        getNotice();
        return view;
    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ShowNoticeData data = snapshot.getValue(ShowNoticeData.class);
                    list.add(0,data);
                }

                adapter = new ShowNoticeAdapter(getContext(),list);
                adapter.notifyDataSetChanged();
                studentProgressBar.setVisibility(View.GONE);
                showNoticeRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                studentProgressBar.setVisibility(View.GONE);

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}