package com.example.btl_laptrinhmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private int curentYear = 0;
    private int curentMonth = 0;
    private int curentDay = 0;

    private int index = 0;

    private List<String> calendarStrings;
    int[] days;
    int[] months;
    int[] years;
    int numberOfDays = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView);


        final List<String> calendarStrings = new ArrayList<>();
        final int numberOfDays = 2000;
        days = new int[numberOfDays];
        months = new int[numberOfDays];
        years = new int[numberOfDays];

        readInfo();

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

                for (int h=0; h<index; h++){
                    if (years[h]==curentYear){
                        for (int i = 0; i<index; i++){
                            if (days[i]==curentDay) {
                                for (int j =0;i<index; i++){
                                    if (months[j]==curentMonth && days[j]==curentDay && years[j]==curentYear){
                                        textInput.setText(calendarStrings.get(j));
                                        return;
                                    }
                                }
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
                calendarStrings.add(index,textInput.getText().toString());
                textInput.setText("");
                index++;
                dayContent.setVisibility(View.GONE);
            }
        });

        final Button todayButton = findViewById(R.id.todayButton);
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              calendarView.setDate(calendarView.getDate());
            }
        });
    }



    @Override
    protected void onPause(){
        super.onPause();
        saveInfo();
    }
    private void saveInfo(){
        File file = new File(this.getFilesDir(),"saved");
        File daysFile = new File(this.getFilesDir(),"days");
        File monthsFile = new File(this.getFilesDir(),"months");
        File yearsFile = new File(this.getFilesDir(), "years");

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fOut));

            FileOutputStream fOutDays = new FileOutputStream(daysFile);
            BufferedWriter bwDay = new BufferedWriter(new OutputStreamWriter(fOutDays));

            FileOutputStream fOutMonths = new FileOutputStream(monthsFile);
            BufferedWriter bwMonths = new BufferedWriter(new OutputStreamWriter(fOutMonths));

            FileOutputStream fOutYears = new FileOutputStream(yearsFile);
            BufferedWriter bwYears = new BufferedWriter(new OutputStreamWriter(fOutYears));

            for (int i  = 0; i<index; i++){
                bw.write(calendarStrings.get(i));
                bw.newLine();
                bwDay.write(days[i]);
                bwMonths.write(months[i]);
                bwYears.write(years[i]);
            }

            bw.close();
            fOut.close();
            bwDay.close();
            fOutDays.close();
            bwMonths.close();
            fOutMonths.close();
            bwYears.close();
            fOutYears.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void readInfo(){
        File file = new File(this.getFilesDir(),"saved");
        File daysFile = new File(this.getFilesDir(),"days");
        File monthsFile = new File(this.getFilesDir(),"months");
        File yearsFile = new File(this.getFilesDir(), "years");

        if (file.exists()){
            return;
        }

    }

}