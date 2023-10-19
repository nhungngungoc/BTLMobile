package com.example.btl_laptrinhmobile.Calendar_Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver
{

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent myIntent = new Intent(context, MyMusic.class);
        context.startForegroundService(myIntent);
    }
}
