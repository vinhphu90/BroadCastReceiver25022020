package com.examplevinhphutvp.broadcastreceiver25022020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Loại 1 : Broad lang nghe khi activity con ton tai
        // Loai 2 : Broad lang nghe khi  kill activity (Service)
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Toast.makeText(context, "Da ket noi voi 3g", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Ban khong co 3g", Toast.LENGTH_SHORT).show();
                    }
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        Toast.makeText(context, "Da ket noi voi wifi", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Ban khong wifi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Ban khong co ket noi", Toast.LENGTH_SHORT).show();
                }

        }
    };
}
