package com.violinMD.shhh;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check for permission
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }

        //get & set vibration setting
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        switch (am.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(getApplicationContext(),"Notification set to NORMAL", Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(getApplicationContext(),"Notification set to SILENT", Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(getApplicationContext(),"Notification set to VIBRATE", Toast.LENGTH_SHORT).show();
                break;
        }

        //terminate
        finish();
    }


}