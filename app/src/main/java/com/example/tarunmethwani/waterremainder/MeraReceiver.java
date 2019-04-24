package com.example.tarunmethwani.waterremainder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Date;

public class MeraReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    public MeraReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "please drink water", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder builder =new NotificationCompat.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        builder.setContentText("water remainder");
        builder.setSubText("please drink water " + new Date());
        NotificationManager nm=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify(1,builder.build());
        mp=MediaPlayer.create(context,R.raw.alarm);
        mp.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mp.stop();

            }
        }).start();
        Intent i=new Intent(context,MeraReceiver.class);
         PendingIntent pi=PendingIntent.getBroadcast(context,1234,i,0);
        AlarmManager am= (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        long time = System.currentTimeMillis()+10000;
        am.set(AlarmManager.RTC_WAKEUP,time,pi);


        throw new UnsupportedOperationException("Not yet implemented");
    }
}
