package com.lamsal.pawan.yclnepal.studentapp.ui.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamsal.pawan.yclnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowNoticeAdapter extends RecyclerView.Adapter<ShowNoticeAdapter.ShowNoticeViewAdapter> {

    private Context context;
    private ArrayList<ShowNoticeData> list;

    public ShowNoticeAdapter(Context context, ArrayList<ShowNoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShowNoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_newsfeed_item_layout, parent, false);
        return new ShowNoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowNoticeViewAdapter holder,final int position) {

        ShowNoticeData currentItem = list.get(position);

        holder.showNoticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());


        try {
            if (currentItem.getImage() != null)
                Picasso.get().load(currentItem.getImage()).into(holder.showNoticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShowNoticeViewAdapter extends RecyclerView.ViewHolder {

        private TextView showNoticeTitle, date, time;
        private ImageView showNoticeImage;


        public ShowNoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            showNoticeTitle = itemView.findViewById(R.id.showNoticeTitle);
            showNoticeImage = itemView.findViewById(R.id.showNoticeImage);
        }
    }
}
