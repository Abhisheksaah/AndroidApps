package com.example.calenderevents.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calenderevents.R;
import com.example.calenderevents.model.Reminder;
import com.example.calenderevents.presentation.utils.DeleteItem;
import com.example.calenderevents.presentation.view_adapter.ReminderAdapter;
import com.example.calenderevents.repository.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DeleteItem {


    CalendarView calendarView;

    TextView label_tv;

    RecyclerView reminder_rev;
    FloatingActionButton addForm_btn;

    EditText editTextForm;
    FloatingActionButton submit_btn, cancel_btn;

    LinearLayout edit_buttons;

    Repository repository;
    LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        repository = new Repository(this);
        selectedDate = LocalDate.now();

        showReminders();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = LocalDate.of(year, month, dayOfMonth);
                showReminders();
            }
        });

        addForm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditFormToggle();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListToggle();
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reminder newReminder = new Reminder(
                        editTextForm.getText().toString(),
                        selectedDate
                );

                repository.addReminder(newReminder);
                editTextForm.setText("");
                showReminders();
                showListToggle();
            }
        });

    }

    void init()
    {
        calendarView = findViewById(R.id.calender_view);
        label_tv = findViewById(R.id.reminder_msg_tv);

        reminder_rev = findViewById(R.id.reminders_rv);
        addForm_btn = findViewById(R.id.addForm_btn);

        editTextForm = findViewById(R.id.editText_id);
        submit_btn = findViewById(R.id.submitForm_btn);
        cancel_btn = findViewById(R.id.cancel_btn);

        edit_buttons = findViewById(R.id.edit_buttons);
    }


    void showReminders()
    {
        List<Reminder> reminders = repository.getEventsOf(selectedDate);

        if(reminders.size()==0)
            label_tv.setText("No Reminders Today");
        else
            label_tv.setText("Your Reminders");

        ReminderAdapter reminderAdapter = new ReminderAdapter(this,reminders);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        reminder_rev.setAdapter(reminderAdapter);
        reminder_rev.setLayoutManager(layoutManager);
    }

    void showListToggle()
    {
        editTextForm.setVisibility(View.GONE);
        edit_buttons.setVisibility(View.GONE);

        reminder_rev.setVisibility(View.VISIBLE);
        addForm_btn.setVisibility(View.VISIBLE);
    }
    void showEditFormToggle()
    {
        reminder_rev.setVisibility(View.GONE);
        addForm_btn.setVisibility(View.GONE);

        editTextForm.setVisibility(View.VISIBLE);
        edit_buttons.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDeleteItem(Reminder reminder) {
        repository.deleteReminder(reminder);
        showReminders();
    }
}