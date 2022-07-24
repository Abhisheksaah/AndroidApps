package com.example.calenderevents.presentation.view_holder;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calenderevents.R;

public class ReminderHolder extends RecyclerView.ViewHolder {

    public TextView description_tv;
    public ImageButton delete_btn;

    public ReminderHolder(@NonNull View itemView) {
        super(itemView);
        description_tv = itemView.findViewById(R.id.description_tv);
        delete_btn = itemView.findViewById(R.id.delete_btn);
    }
}
