package com.lamsal.pawan.yclnepal.studentapp.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.lamsal.pawan.yclnepal.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private CourseAdapter adapter;
    private List<CourseModel> list;
    private ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);
        list = new ArrayList<>();
        list.add(new CourseModel(R.drawable.logo,"Karyalaya",R.string.Karyalaya));
        list.add(new CourseModel(R.drawable.logo,"Sachivalaya",R.string.Sachivalaya));
        list.add(new CourseModel(R.drawable.logo,"Central Committee",R.string.central));
        list.add(new CourseModel(R.drawable.logo,"Aayog & Bibhag",R.string.aayog));

        adapter = new CourseAdapter(getContext(),list);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.college_image);
        Glide.with(getContext()).load(R.drawable.ycl).into(imageView);

        map = view.findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }
    private void openMap() {
        Uri uri = Uri.parse("geo:0,0?q=Communist+Party+of+Nepal+(Maoist+Center)");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}