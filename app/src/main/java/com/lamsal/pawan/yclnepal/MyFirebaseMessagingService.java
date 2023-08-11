package com.lamsal.pawan.yclnepal;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());


    }

    public void getFirebaseMessage(String title,String msg){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myFirebaseChannel")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("New Notice Uploaded")
                .setContentText("YCL Nepal uploaded a new notice you may like to see it.")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1,builder.build());

    }

}
