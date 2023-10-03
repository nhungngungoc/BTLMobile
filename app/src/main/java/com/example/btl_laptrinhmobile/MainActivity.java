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
    private int curentYear = 0;
    private int curentMonth = 0;
    private int curentDay = 0;

    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView);


        final List<String> calendarStrings = new ArrayList<>();
        final int numberOfDays = 2000;
        final int[] days = new int[numberOfDays];
        final int[] months = new int[numberOfDays];
        final int[] years = new int[numberOfDays];

        final EditText textInput = findViewById(R.id.textInput);

        final View dayContent = findViewById(R.id.dayContent);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth)
            {
                curentYear = year;
                curentMonth = month;
                curentDay = dayOfMonth;
                if (dayContent.getVisibility() == View.GONE){
                    dayContent.setVisibility(View.VISIBLE);
                }



                for (int i = 0; i<30; i++){
                    if (days[i]==curentDay) {
                        for (int j =0;i<12; i++){
                            if (months[j]==curentMonth){
                                textInput.setText(calendarStrings.get(i));
                                return;
                            }
                        }
                    }
                }
                textInput.setText("");
            }
        });

        final Button saveTextButton = findViewById(R.id.saveTextButton);

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days[index] = curentDay;
                months[index] = curentMonth;
                years[index] = curentYear;
                index++;
                calendarStrings.add(index,textInput.getText().toString());
                textInput.setText("");
            }
        });
    }
}