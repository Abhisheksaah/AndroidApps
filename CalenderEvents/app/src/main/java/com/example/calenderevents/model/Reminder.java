package com.example.calenderevents.model;

import java.time.LocalDate;

public class Reminder {
    public String description;
    public LocalDate date;

    public Reminder(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }
}
