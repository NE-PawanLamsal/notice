package com.lamsal.pawan.yclnepal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.lamsal.pawan.yclnepal.R;
import com.lamsal.pawan.yclnepal.faculty.UpdateFaculty;
import com.lamsal.pawan.yclnepal.notice.DeleteNoticeActivity;
import com.lamsal.pawan.yclnepal.notice.UploadNotice;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
CardView uploadNotice , addEbook, faculty,deleteNotice;
private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice = findViewById(R.id.addNotice);
        addEbook = findViewById(R.id.addEbook);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        logout=findViewById(R.id.logout);
        uploadNotice.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);

            }
        });

        getSupportActionBar().hide();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId() ){
            case R.id.addNotice:
                intent = new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent(MainActivity.this, UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent = new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
        }


    }

    public void logout(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        preferences.clearData(this);
        finish();
    }
}