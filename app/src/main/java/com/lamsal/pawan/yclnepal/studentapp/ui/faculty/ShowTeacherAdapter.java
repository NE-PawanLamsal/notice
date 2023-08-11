package com.lamsal.pawan.yclnepal.studentapp.ui.faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamsal.pawan.yclnepal.R;
import com.lamsal.pawan.yclnepal.faculty.TeacherData;
import com.lamsal.pawan.yclnepal.faculty.UpdateTeacherActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowTeacherAdapter extends RecyclerView.Adapter<ShowTeacherAdapter.ShowTeacherViewAdapter> {
    private List<ShowTeacherData> list;
    private Context context;

    public ShowTeacherAdapter(List<ShowTeacherData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowTeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_faculty_item_layout, parent, false);
        return new ShowTeacherViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowTeacherAdapter.ShowTeacherViewAdapter holder, int position) {

        ShowTeacherData item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());
        try {
            Picasso.get().load(item.getImage()).placeholder(R.drawable.avatar).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShowTeacherViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, post;
        private ImageView imageView;

        public ShowTeacherViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentTeacherName);
            email = itemView.findViewById(R.id.studentTeacherEmail);
            post = itemView.findViewById(R.id.studentTeacherPost);
            imageView = itemView.findViewById(R.id.studentTeacherImage);
        }
    }
}