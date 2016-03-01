package ypsitos.notificationlab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

        public static final int NOTIFICATION_AVAILABLE = 1;
        public static final int NOTIFICATION_NOT_AVAILABLE = 2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                networkAvailableNotify();
            } else {
                networkNotAvailableNotify();
            }
        }

        private void networkNotAvailableNotify() {
            android.support.v7.app.NotificationCompat.BigPictureStyle bigPictureStyle = new android.support.v7.app.NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.nonetwork)).build();
            android.support.v7.app.NotificationCompat.Builder mBuilder = new android.support.v7.app.NotificationCompat.Builder(this);

            Intent intent = new Intent(this, SecondActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

            mBuilder.setContentIntent(pIntent);
            mBuilder.setSmallIcon(R.drawable.ic_network_locked);
            mBuilder.setContentTitle("Attention!");
            mBuilder.setContentText("The network(s) in your location are not available!");

            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigPictureStyle);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_NOT_AVAILABLE, mBuilder.build());
        }

        private void networkAvailableNotify() {
            android.support.v7.app.NotificationCompat.Builder mBuilder = new android.support.v7.app.NotificationCompat.Builder(this);
            android.support.v7.app.NotificationCompat.BigPictureStyle bigPictureStyle = new android.support.v7.app.NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.yesnetwork)).build();

            Intent intent = new Intent(this, SecondActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

            mBuilder.setContentIntent(pIntent);
            mBuilder.setStyle(bigPictureStyle);
            mBuilder.setSmallIcon(R.drawable.ic_network_cell);
            mBuilder.setContentTitle("Attention!");
            mBuilder.setContentText("The network(s) in your location are available and defaults are set!");

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_AVAILABLE, mBuilder.build());
        }

    }
