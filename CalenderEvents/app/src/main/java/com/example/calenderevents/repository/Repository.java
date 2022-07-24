package com.example.calenderevents.repository;

import android.content.Context;

import com.example.calenderevents.data_src.database.DBHandler;
import com.example.calenderevents.data_src.demo_data.Data;
import com.example.calenderevents.model.Reminder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    DBHandler dbHandler;

    public Repository(Context context) {
        this.dbHandler = new DBHandler(context);
    }

    public List<Reminder> getEventsOf(LocalDate date)
    {
        List<Reminder> rems = new ArrayList<>();

        for (Reminder reminder: dbHandler.getAllReminder())
        {
            if(reminder.date.equals(date))
                rems.add(reminder);
        }

        return rems;
    }

    public void addReminder(Reminder reminder)
    {
        dbHandler.addReminder(reminder);
    }

    public void deleteReminder(Reminder reminder)
    {
        dbHandler.deleteReminder(reminder);
    }

}
