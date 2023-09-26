package com.example.btl_laptrinhmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView);
        final TextView selectedDay = findViewById(R.id.selectedDay);
        final TextView selectedMonth = findViewById(R.id.selectedMonth);
        final TextView selectedYear = findViewById(R.id.selectedYear);


        final List<String> calendarStrings = new ArrayList<>();
        final EditText textInput = findViewById(R.id.textInput);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth)
            {
                selectedDay.setText("Selcted Day:" +dayOfMonth);
                selectedMonth.setText("Selcted Month:" + month);
                selectedYear.setText("Selcted Year:" + year);
                long saveDate = Long.parseLong(calendarStrings.get(0));
                if (calendarView.getDate() == saveDate)
                {
                    textInput.setText(calendarStrings.get(1));
                }
            }
        });

        final Button saveTextButton = findViewById(R.id.saveTextButton);

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarStrings.add(String.valueOf(calendarView.getDate()));
                calendarStrings.add(textInput.getText().toString());
                textInput.setText("");
            }
        });
    }
}