package com.example.calenderevents.data_src.demo_data;

import com.example.calenderevents.model.Reminder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static List<Reminder> reminders = new ArrayList<>(
            Arrays.asList(
                    new Reminder("Sleeping...", LocalDate.now()),
                    new Reminder("Playing...",LocalDate.now()),
                    new Reminder("Eating...",LocalDate.now())
            )
    );
}
