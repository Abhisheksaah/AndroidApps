package com.example.calenderevents.presentation.view_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calenderevents.R;
import com.example.calenderevents.model.Reminder;
import com.example.calenderevents.presentation.utils.DeleteItem;
import com.example.calenderevents.presentation.view_holder.ReminderHolder;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderHolder> {

    private List<Reminder> reminders;
    private DeleteItem context;

    public ReminderAdapter(DeleteItem context,List<Reminder> reminders)
    {
        this.context = context;
        this.reminders = reminders;

    }

    @NonNull
    @Override
    public ReminderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new ReminderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.description_tv.setText(reminder.description);

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onDeleteItem(reminder);
            }
        });

    }


    @Override
    public int getItemCount() {
        return reminders.size();
    }
}
